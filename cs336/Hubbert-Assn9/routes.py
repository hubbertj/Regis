# Name: Jerum Lee Hubbert
# AssignmentL CS336 Assignment #8
# Created: 08/03/2020
# Description: Routes file for management of flask server

from flask import request, abort, jsonify, render_template, Blueprint, send_from_directory

route_all = Blueprint('route_all', __name__)
route_static = Blueprint('route_static', __name__)
route_error = Blueprint('route_error', __name__)


@route_all.route('/')
def index():
    return render_template('index.html')


@route_all.route('/activities')
def activities():
    return render_template('activities.html')


@route_all.route('/awards')
def awards():
    return render_template('awards.html')


@route_all.route('/keynote')
def keynote():
    return render_template('keynote.html')


@route_all.route('/meals')
def meals():
    return render_template('meals.html')


@route_all.route('/poll', methods=['GET', 'POST', 'PUT', 'DELETE'])
def poll():
    if request.method == 'GET':
        return render_template('poll.html')
    elif request.method == 'POST':
        print(request.form)
        return jsonify(process=True, message='')
    elif request.method == 'PUT':
        print(request.form)
        return jsonify(process=True, message='')
    elif request.method == 'DELETE':
        print(request.form)
        return jsonify(process=True, message='')
    else:
        abort(401)


@route_all.route('/registration', methods=['GET', 'POST', 'PUT', 'DELETE'])
def registration():
    if request.method == 'GET':
        return render_template('registration.html')
    elif request.method == 'POST':
        print(request.form)
        return jsonify(process=True, message='')
    elif request.method == 'PUT':
        print(request.form)
        return jsonify(process=True, message='')
    elif request.method == 'DELETE':
        print(request.form)
        return jsonify(process=True, message='')
    else:
        abort(401)


@route_all.route('/thankyou')
def thank_you():
    return render_template('thankyou.html')


@route_all.route('/workshopschedule')
def workshop_schedule():
    return render_template('workshopschedule.html')


@route_all.route('/nametags/nametags8gen')
def name_tags_8():
    return render_template('nametags/nametags8gen.html')


@route_all.route('/nametags/nametags10gen')
def name_tags_10():
    return render_template('nametags/nametags10gen.html')


@route_all.route('/admin')
def admin():
    return render_template('admin.html')


@route_static.route('/js/<path:path>')
def send_js(path):
    return send_from_directory('js', path)


@route_static.route('/images/<path:path>')
def send_images(path):
    return send_from_directory('images', path)


@route_static.route('/css/<path:path>')
def send_css(path):
    return send_from_directory('css', path)


@route_static.route('/fonts/<path:path>')
def send_fonts(path):
    return send_from_directory('fonts', path)


@route_static.route('/static/<path:path>')
def send_static(path):
    return send_from_directory('static', path)

