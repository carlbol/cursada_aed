#Colas
from queue import Queue as cola
import random
def generar_nros_al_azar(n:int, desde:int, hasta:int) -> cola:
    cola_resultado:cola = cola()
    for i in range(n):
        entero_azaroso:int = random.randint(desde,hasta)
        cola_resultado.put(entero_azaroso)
    return cola_resultado


def cantidad_elementos(c:cola) -> int:
    contador:int = 0
    c_auxiliar:cola = cola()
    while not c.empty():
        c_auxiliar.put(c.get())
        contador += 1
    while not c_auxiliar.empty():
        c.put(c_auxiliar.get())
    return contador

cola_ejemplo = generar_nros_al_azar(10,1,10)
#print(cantidad_elementos(cola_ejemplo))

def buscar_el_maximo(c:cola) -> int:
    elementos:[int] = []
    c_auxiliar:cola = cola()

    while not c.empty():
        elemento:int = c.get()
        elementos.append(elemento)
        c_auxiliar.put(elemento)
    while not c_auxiliar.empty():
        elemento:int = c_auxiliar.get()
        c.put(elemento)
    return max(elementos)

'''print(buscar_el_maximo(cola_ejemplo))'''