<template>

  <div id="content-wrapper">
    <header id="header">
      <h1>Proxy Playing</h1>
    </header>

    <main>
      <div id="proxy-example-wrapper">
        <div class="music-item">
          <h3>With Proxy</h3>

          <h2 v-if="proxySongDisplay !== ''">
            {{ proxySongDisplay }}
          </h2>

          <button
            class="play-button"
            @click="getSongUsingProxy"
          >
          Play ⏵
          </button>
          <h4>{{ proxyLoadTime }}</h4>
        </div>

        <div class="music-item">
          <h3>No Proxy</h3>

          <h2 v-if="noProxySongDisplay !== ''">
            {{ noProxySongDisplay }}
          </h2>

          <button
            class="play-button"
            @click="getSongUsingNoProxy"
          >
          Play ⏵
          </button>
          <h4>{{ noProxyLoadTime }}</h4>
        </div>
      </div>
    </main>

  </div>

</template>

<script lang="ts">
import { ref } from 'vue'

export default {
  setup() {
    const BASE_URL = 'http://127.0.0.1:5000'
    const MAX_SONGS = 3
    const proxyLoadTime = ref('Awaiting analysis...')
    const noProxyLoadTime = ref('Awaiting analysis...')
    const proxySongDisplay = ref('')
    const noProxySongDisplay = ref('')

    const retrieveDataAndTimeIt = async (url, requestAttributes) => {
      const startTime = performance.now()

      const result = await fetch(url, requestAttributes)
      .then(async (response) => {
        return response.json().then((json) => {
          const endTime = performance.now()
          const timeTaken = endTime - startTime
          return {
            timeTaken: timeTaken.toFixed(2).toString() + ' ms',
            songDisplay: json.songDisplay
          }
        })
      })
      .catch((error) => {
        console.log(error)
      })

      return result
    }

    const generateSongId = (maxSongs) => {
      return Math.floor(Math.random() * maxSongs) + 1
    }

    const getSongUsingProxy = async () => {
      const songId = generateSongId(MAX_SONGS)
      const result = await retrieveDataAndTimeIt(`${BASE_URL}/api/play/proxy?songId=${songId}`, { method: 'GET' })
      proxyLoadTime.value = result.timeTaken
      proxySongDisplay.value = result.songDisplay
    }

    const getSongUsingNoProxy = async () => {
      const songId = generateSongId(MAX_SONGS)
      const result = await retrieveDataAndTimeIt(`${BASE_URL}/api/play?songId=${songId}`, { method: 'GET' })
      noProxyLoadTime.value = result.timeTaken
      noProxySongDisplay.value = result.songDisplay
    }

    return {
      proxyLoadTime,
      noProxyLoadTime,
      proxySongDisplay,
      noProxySongDisplay,
      getSongUsingProxy,
      getSongUsingNoProxy,
    }
  }
}

</script>

<style scoped>

#content-wrapper {
  display: flex;
  height: 90vh;
  width: 100%;
  flex-direction: column;
  align-items: center;
  justify-content: space-evenly;
  position: relative;
  overflow: hidden;
}

#header {
  text-decoration: underline;
}

#proxy-example-wrapper {
  display: flex;
  width: 100vw;
  flex-direction: row;
  align-items: baseline;
  justify-content: space-evenly;
  position: relative;
}

.play-button {
  font-size: 1.2em;
  width: 15vw;
  height: 10vh;
}

</style>
