# Name: Jerum Hubbert
# AssignmentL CS336 Assignment #7
# Created: 07/15/2020
# Description: Python script for reading name tags

import csv
import os
import sys
from string import Template

import django
from django.conf import settings
from django.template.defaultfilters import register
from django.template.loader import render_to_string

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DEFAULT_DIR = os.path.join(BASE_DIR, "templates")
DEFAULT_DATA_DIR = os.path.join(BASE_DIR, "database_code")
STATIC_DIR = os.path.join(BASE_DIR, "static")

TEMPLATE = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [DEFAULT_DIR, DEFAULT_DATA_DIR, STATIC_DIR],
        'APP_DIRS': True,
        'OPTIONS': {},
    }
]

class test:
    def __init__(self, frist, last, pos):
        self.fristName = frist
        self.lastName = last
        self.position = pos

person = test('Jill', 'Jackson', 'Web Programmer')

if not os.path.exists(os.path.dirname(os.path.dirname(os.path.abspath(__file__))) + '/output.txt'):
    os.makedirs(os.path.dirname(os.path.dirname(os.path.abspath(__file__))) + '/output.txt')
with open(os.path.dirname(os.path.dirname(os.path.abspath(__file__))) + '/output.txt', 'w') as file:
    file.write(' '.join(["%s\n" % (k,v) for k,v in person.__dict__.iteritems()]))


@register.filter
def modulo(num, val):
    return num % val


def init():
    settings.configure(TEMPLATES=TEMPLATE)
    django.setup()


def generate_context(file_name):
    try:
        with open(DEFAULT_DATA_DIR + '\\' + file_name, 'r', encoding='utf-8') as file:
            reader = csv.reader(file)
            guest_list = list(reader)
    except FileNotFoundError:
        print(file_name + " wasn’t found!")
        sys.exit(1)
    return {
        'guests': guest_list, }


def generate_html(title_name, template_name, ctx):
    ctx['title'] = title_name
    template = render_to_string(template_name, ctx)
    return template


def save_to_html(name, ctx):
    save_dir = DEFAULT_DIR + '/nametags'
    if not os.path.exists(save_dir):
        os.makedirs(save_dir)
    with open(Template('$dir/$file_name.html').substitute(file_name=name, dir=save_dir), "w") as file:
        file.write(ctx)
    return None


init()
context = generate_context('registrant_data.csv')

tag_8_context = generate_html('nametags8', 'nametags8.html', context)
save_to_html('nametags8gen', tag_8_context)

tag_10_context = generate_html('nametags10', 'nametags10.html', context)
save_to_html('nametags10gen', tag_10_context)
