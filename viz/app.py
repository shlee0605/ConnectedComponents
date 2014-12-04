from flask import Flask, render_template
from flask.ext.bootstrap import Bootstrap

import json

app = Flask(__name__)

bootstrap = Bootstrap(app)


@app.errorhandler(404)
def page_not_found(e):
    return render_template('404.html'), 404


@app.errorhandler(500)
def internal_server_error(e):
    return render_template('500.html'), 500


@app.route('/')
def index():
    return render_template('index.html')

@app.route("/graph")
@app.route("/graph/<int:ndata>")
def data(ndata=1):
    if ndata > 1:
        return render_template('graph' + str(ndata) + '.html')
    return render_template('graph.html')

if __name__ == '__main__':
    app.run()
