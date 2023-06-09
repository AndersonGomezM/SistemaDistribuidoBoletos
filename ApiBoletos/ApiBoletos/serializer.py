from rest_framework import serializers
from .models import Boletos

class BoletosSerializer(serializers.ModelSerializer):
    class Meta:
            model = Boletos
            # fields = ('fullname', 'nickname')
            fields = '__all__'