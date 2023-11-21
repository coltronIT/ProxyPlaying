import json
from time import sleep
from flask import Flask
from flask_cors import CORS

app = Flask(__name__)
CORS(app)


@app.route('/api/all_songs')
def get_song_data():
    sleep(5)
    return json.dumps({
        'songs':
            {
                '1': 'Cruel Summer: 5%',
                '2': 'Anti-Hero: 20%',
                '3': 'Lovestory: 100%'
            }
    })
