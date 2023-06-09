from rest_framework import viewsets
from .serializer import BoletosSerializer
from .models import Boletos

class BoletosViewSet(viewsets.ModelViewSet):
    queryset = Boletos.objects.all()
    serializer_class = BoletosSerializer