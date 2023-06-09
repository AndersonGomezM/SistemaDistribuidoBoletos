from django.db import models

class Boletos(models.Model):
    NombreBoleto = models.CharField(max_length=100)
    NombreEvento = models.CharField(max_length=100)
    CantidadBoletos = models.PositiveSmallIntegerField()