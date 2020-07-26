# Name: Jerum Hubbert
# AssignmentL CS336 Assignment #7
# Created: 07/15/2020
# Description: Python script for reading name tags

import django
from django.template import Template, Context
from django.conf import settings
from django.template.loader import get_template, render_to_string
from django.template import Context

TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': ['../'],
        'APP_DIRS': True,
        'OPTIONS': {
            # ... some options here ...
        },
    }
]

settings.configure(TEMPLATES=TEMPLATES)  # We have to do this to use django templates standalone - see
# http://stackoverflow.com/questions/98135/how-do-i-use-django-templates-without-the-rest-of-django

django.setup()

name_tags8_template = get_template('nametags8.html')

c = {'name': 'world'}

print(name_tags8_template.render(c))

