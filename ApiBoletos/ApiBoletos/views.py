from rest_framework import viewsets
from .serializer import EventosSerializer
from .models import Eventos
from .serializer import BoletosSerializer
from .models import Boletos

class EventosViewSet(viewsets.ModelViewSet):
    queryset = Eventos.objects.all()
    serializer_class = EventosSerializer

class BoletosViewSet(viewsets.ModelViewSet):
    queryset = Boletos.objects.all()
    serializer_class = BoletosSerializer