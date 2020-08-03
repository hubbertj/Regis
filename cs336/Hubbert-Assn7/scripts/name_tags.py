# Name: Jerum Hubbert
# AssignmentL CS336 Assignment #7
# Created: 07/15/2020
# Description: Python script for reading name tags

import sys
import django
from django.conf import settings
from string import Template

from django.template.defaultfilters import register
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


@register.filter
def modulo(num, val):
    return num % val


def init():
    settings.configure(TEMPLATES=TEMPLATE)
    django.setup()


def generate_context(file_name):
    try:
        with open(DEFAULT_DATA_DIR + file_name, 'r', encoding='utf-8') as file:
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
    with open(Template('$dir/$file_name.html').substitute(file_name=name, dir='../nametags'), "w") as file:
        file.write(ctx)
    return None


init()
context = generate_context('registrant_data.csv')

tag_8_context = generate_html('nametags8', 'nametags8.html', context)
save_to_html('nametags8gen', tag_8_context)

tag_10_context = generate_html('nametags10', 'nametags10.html', context)
save_to_html('nametags10gen', tag_10_context)
