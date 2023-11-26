from time import sleep
from flask import (
    Flask,
    jsonify,
)
from flask_cors import CORS

app = Flask(__name__)
CORS(app)


@app.route('/api/all_songs')
def get_song_data():
    sleep(5)

    songs = [
        {
            'id': '1',
            'songDisplay': 'Cruel Summer - 10%',
        },
        {
            'id': '2',
            'songDisplay': 'Anti-Hero - 20%',
        },
        {
            'id': '3',
            'songDisplay': 'Lovestory - 100%',
        }
    ]

    return jsonify(songs)