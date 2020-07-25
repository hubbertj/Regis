# Name: Jerum Hubbert
# AssignmentL CS336 Assignment #7
# Created: 07/15/2020
# Description: Python script for reading name tags

import os


# from django.conf import settings
# settings.configure() # We have to do this to use django templates standalone - see
# http://stackoverflow.com/questions/98135/how-do-i-use-django-templates-without-the-rest-of-django

# Our template. Could just as easily be stored in a separate file

import django
from django.conf import settings
from django.template.loader import render_to_string
from django.template.loader import get_template
from django.template import Context

TEMPLATES = [
    {
        'BACKEND': 'django.template.backends.django.DjangoTemplates',
        'DIRS': ['../templates'],
    }
]
settings.configure(TEMPLATES=TEMPLATES)
django.setup()
template = get_template('my_template.html')
message = render_to_string('my_template.html', Context)