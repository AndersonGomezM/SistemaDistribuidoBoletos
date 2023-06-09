from rest_framework import serializers
from .models import Eventos
from .models import Boletos

class EventosSerializer(serializers.ModelSerializer):
    class Meta:
            model = Eventos
            # fields = ('fullname', 'nickname')
            fields = '__all__'

class BoletosSerializer(serializers.ModelSerializer):
    class Meta:
            model = Boletos
            # fields = ('fullname', 'nickname')
            fields = '__all__'