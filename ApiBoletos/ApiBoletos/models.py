from django.db import models
from django.utils.timezone import now

class Eventos(models.Model):
    nombre = models.CharField(max_length=100)
    descripcion = models.CharField(max_length=10000)
    fecha = models.DateTimeField(default=now, blank=True)
    direccion = models.CharField(max_length=200)

class Boletos(models.Model):
    eventos = models.ForeignKey(Eventos, on_delete=models.CASCADE)
    nombre = models.CharField(max_length=100)
    cantidad = models.PositiveSmallIntegerField()
    def __str__(self):
        return self.eventos
    
    class Meta:
        ordering = ["eventos"]