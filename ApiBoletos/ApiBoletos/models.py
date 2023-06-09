from django.db import models
from django.utils.timezone import now

class Eventos(models.Model):
    Nombre = models.CharField(max_length=100)
    Descripcion = models.CharField(max_length=10000)
    Fecha = models.DateTimeField(default=now, blank=True)

class Boletos(models.Model):
    NombreBoleto = models.CharField(max_length=100)
    Precio = models.FloatField(max_length=100000)
    CantidadBoletos = models.PositiveSmallIntegerField()