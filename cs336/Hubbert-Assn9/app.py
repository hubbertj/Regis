# Name: Jerum Lee Hubbert
# AssignmentL CS336 Assignment #8
# Created: 08/03/2020
# Description: Server file for management of flask server

from flask import Flask, send_from_directory
from dotenv import load_dotenv
from os import environ
from models.shared.models import db


def create_app():
    app = Flask(__name__)

    @app.errorhandler(404)
    def page_not_found():
        return render_template('404.html')

    @app.errorhandler(401)
    def unauthorized_request():
        return 'User cannot perform this action', 401

    return app


def add_static(app):
    @app.route('/js/<path:path>')
    def send_js(path):
        return send_from_directory('js', path)

    @app.route('/images/<path:path>')
    def send_images(path):
        return send_from_directory('images', path)

    @app.route('/css/<path:path>')
    def send_css(path):
        return send_from_directory('css', path)

    @app.route('/fonts/<path:path>')
    def send_fonts(path):
        return send_from_directory('fonts', path)

    @app.route('/static/<path:path>')
    def send_static(path):
        return send_from_directory('static', path)

    return app


load_dotenv('.env')
application = create_app()
application = add_static(application)
application.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///conference.sqlite'
application.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db.init_app(application)

with application.test_request_context():

    db.create_all()

if environ.get('FLASK_ENV'):
    print('Running in ' + environ.get('FLASK_ENV') + ' environment')

if __name__ == '__main__':
    from routes import *

    application.run(port=environ.get('PORT'))
