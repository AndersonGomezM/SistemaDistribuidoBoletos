from django.urls import path, include
from rest_framework import routers
from ApiBoletos import views

router = routers.DefaultRouter()
router.register(r'boletos', views.BoletosViewSet)

urlpatterns = [
    path('', include(router.urls))
]
