<template>

  <div id="content-wrapper">
    <header id="header">
      <h1>Proxy Playing</h1>
    </header>

    <main>
      <div id="proxy-example-wrapper">
        <div class="music-item">
          <h3>With Proxy</h3>
          <button
            class="play-button"
            @click="getSongUsingProxy"
          >
          Play ⏵
          </button>
          <h4>{{ proxyLoadTime }}</h4>
        </div>

        <div class="music-item">
          <h3>Performance Battle</h3>
          <button
            @click="performanceBattle"
          >
          VS
          </button>
          <h4></h4>
        </div>
    
        <div class="music-item">
          <h3>No Proxy</h3>
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
    const proxyLoadTime = ref('Awaiting analysis...')
    const noProxyLoadTime = ref('Awaiting analysis...')

    const timeIt = async (url, requestAttributes) => {
      const startTime = performance.now()

      const result = await fetch(url, requestAttributes)
      .then(async (response) => {
        return response.json().then((_) => {
          const endTime = performance.now()
          return endTime - startTime
        })
      })
      .catch((error) => {
        console.log(error)
      })

      return result.toFixed(2).toString() + ' ms'
    }

    const getSongUsingProxy = async () => {
      proxyLoadTime.value = await timeIt(`${BASE_URL}/api/play/proxy`, { method: 'GET' })
    }

    const getSongUsingNoProxy = async () => {
      noProxyLoadTime.value = await timeIt(`${BASE_URL}/api/play`, { method: 'GET' })
    }

    const performanceBattle = async () => {
      await getSongUsingNoProxy()
      await getSongUsingProxy()
    }

    return {
      proxyLoadTime,
      noProxyLoadTime,
      getSongUsingProxy,
      getSongUsingNoProxy,
      performanceBattle,
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
