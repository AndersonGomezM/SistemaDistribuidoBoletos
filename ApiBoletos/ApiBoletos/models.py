from django.db import models
from django.utils.timezone import now

class Eventos(models.Model):
    nombre = models.CharField(max_length=100)
    descripcion = models.CharField(max_length=10000)
    fecha = models.DateTimeField(default=now, blank=True)
    direccion = models.CharField(max_length=200)

class Boletos(models.Model):
    nombre = models.CharField(max_length=100)
    precio = models.FloatField(max_length=100000)
    cantidad = models.PositiveSmallIntegerField()
    eventos = models.ForeignKey(Eventos, on_delete=models.CASCADE)
    def __str__(self):
        return self.nombre
    
    class Meta:
        ordering = ["nombre"]