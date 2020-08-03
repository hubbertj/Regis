# Name: Jerum Hubbert
# AssignmentL CS336 Assignment #7
# Created: 07/15/2020
# Description: Python script for reading name tags

import django
from django.conf import settings
from string import Template
from django.template.loader import render_to_string
import csv

DEFAULT_DATA_DIR = '../data/'
DEFAULT_DIR = '..'
TEMPLATE = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': [DEFAULT_DIR],
        'APP_DIRS': True,
        'OPTIONS': {},
    }
]


def init():
    settings.configure(TEMPLATES=TEMPLATE)
    django.setup()


def generate_context(file_name):
    with open(DEFAULT_DATA_DIR + file_name, 'r', encoding='utf-8') as f:
        reader = csv.reader(f)
        guest_list = list(reader)
    return {
        'guests': guest_list, }


def generate_html(title_name, template_name, ctx):
    ctx['title'] = title_name
    template = render_to_string(template_name, ctx)
    return template


def save_to_html(name, ctx):
    with open(Template('$dir/$file_name.html').substitute(file_name=name, dir='../nametags'), "w") as file:
        file.write(ctx)
    return None


init()
context = generate_context('registrant_data.csv')

tag_8_context = generate_html('nametags8', 'nametags8.html', context)
save_to_html('nametags8gen', tag_8_context)

tag_10_context = generate_html('nametags10', 'nametags10.html', context)
save_to_html('nametags10gen', tag_10_context)
