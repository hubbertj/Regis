# Name: Jerum Lee Hubbert
# AssignmentL CS336 Assignment #8
# Created: 08/03/2020
# Description: Server file for management of flask server

from flask import Flask, render_template, send_from_directory

app = Flask(__name__)


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


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/activities')
def activities():
    return render_template('activities.html')


@app.route('/workshopschedule')
def workshop_schedule():
    return render_template('workshopschedule.html')


@app.route('/meals')
def meals():
    return render_template('meals.html')


@app.route('/keynote')
def keynote():
    return render_template('keynote.html')


@app.route('/awards', methods=['GET'])
def awards():
    return render_template('awards.html')


@app.route('/admin')
def admin():
    return render_template('admin.html')


@app.route('/nametags8')
def name_tags_8():
    return render_template('nametags8.html')


@app.route('/nametags10')
def name_tags_10():
    return render_template('nametags10.html')


@app.route('/test')
def test():
    animal_list = ['dog', 'cat', 'mouse']
    return render_template('test.html', animallist=animal_list)


if __name__ == '__main__':
    app.run(port=3000)
