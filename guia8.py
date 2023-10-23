#Parte 1: Archivos

def contar_lineas(nombre_archivo:str) -> int:
    archivo = open(nombre_archivo, "r")
    return len(archivo.readlines())

def existe_palabra(nombre_archivo:str, palabra:str) -> bool:
    archivo = open(nombre_archivo, "r") 
    contenido = archivo.read()
    return palabra in contenido.split()


def cantidad_aparicones(nombre_archivo:str, palabra:str) -> int:
    archivo = open(nombre_archivo, 'r')
    palabras_archivo = archivo.read()
    return palabras_archivo.split().count(palabra)


def clonar_sin_comentarios(nombre_archivo:str) -> str:
    archivo = open(nombre_archivo, 'r')
    archivo_descomentado = open('/home/clinux01/Descargas/archivo_descomentado.txt', 'w')
    palabras_por_linea = archivo.readlines()
    for linea in palabras_por_linea:
        if linea.strip()[0] != '#':
           archivo_descomentado.write(linea)
            
#print(clonar_sin_comentarios('/home/clinux01/Descargas/ejemplo.txt'))


# El resto de ejercicios...

# Parte 2:Pilas

from queue import LifoQueue as pila

#8,9...

def buscar_el_maximo(p:pila) -> int:
    elementos:list[int] = []
    pila_auxiliar:pila = pila()
    while not p.empty():
        elemento = p.get
        elementos.append(elemento)
        pila_auxiliar.put(elemento)
    while not pila_auxiliar.empty():
        elemento = pila_auxiliar.get()
        pila.put()
    return max(elementos)

# Parte 3: Colas ...

#Parte 4: Diccionarios

def agrupar_por_longitud(nombre_archivo:str) -> dict:
    archivo = open(nombre_archivo, 'r')
    lista_palabras = archivo.read().split()
    longitudes:dict = {}
    
    for palabra in lista_palabras:
        longitud = len(palabra)
        if longitud not in longitudes:
            longitudes[longitud] = 1
        else:
            longitudes[longitud] += 1
    return longitudes
        
print(agrupar_por_longitud('/home/clinux01/Descargas/ejemplo.txt'))




            
        
