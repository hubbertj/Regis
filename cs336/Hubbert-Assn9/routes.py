# Name: Jerum Lee Hubbert
# AssignmentL CS336 Assignment #8
# Created: 08/03/2020
# Description: Routes file for management of flask server

from flask import request, abort, jsonify, render_template
from app import application


@application.route('/')
def index():
    return render_template('index.html')


@application.route('/activities')
def activities():
    return render_template('activities.html')


@application.route('/awards')
def awards():
    return render_template('awards.html')


@application.route('/keynote')
def keynote():
    return render_template('keynote.html')


@application.route('/meals')
def meals():
    return render_template('meals.html')


@application.route('/poll', methods=['GET', 'POST', 'PUT', 'DELETE'])
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


@application.route('/registration', methods=['GET', 'POST', 'PUT', 'DELETE'])
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


@application.route('/thankyou')
def thank_you():
    return render_template('thankyou.html')


@application.route('/workshopschedule')
def workshop_schedule():
    return render_template('workshopschedule.html')


@application.route('/nametags/nametags8gen')
def name_tags_8():
    return render_template('nametags/nametags8gen.html')


@application.route('/nametags/nametags10gen')
def name_tags_10():
    return render_template('nametags/nametags10gen.html')


@application.route('/admin')
def admin():
    return render_template('admin.html')
