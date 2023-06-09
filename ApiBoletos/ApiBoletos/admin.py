from django.contrib import admin
from .models import Eventos
from .models import Boletos

admin.site.register(Eventos)
admin.site.register(Boletos)