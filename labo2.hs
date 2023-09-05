-- Ejercicio 4. Especificar e implementar las siguientes funciones:
{- a) todoMenor: dadas dos tuplas R × R, decide si es cierto que
cada coordenada de la primera tupla es menor a la
coordenada correspondiente de la segunda tupla.

Respuesta: 
Especificación:
    problema todoMenor(tp1,tp2: R X R) : res : Bool {
        requiere: {True}
        asegura: {Si primero de tp1 = primero de tp2 y segundo de tp1 = segundo de tp2 entonces res = True}}
-}
todoMenor :: (Float, Float) -> (Float, Float) -> Bool
todoMenor a b | (fst a) <= (fst b) && (snd a) <= (snd b)  = True
            |otherwise = False 

{- b
 posPrimerPar: dada una terna de enteros, devuelve la
posici´on del primer n´umero par si es que hay alguno, y
devuelve 4 si son todos impares

Rta:
    problema posPrimerPar(t1: RxRxR): Int {
    requiere: {True}
    asegura: {Sea n1, n2, n3 el primer, segundo y tercer elemento de la terna, respectivamente.
    n1 es par -> res = 1}
    asegura: {n2 es par y n1 es impar -> res = 2}
    asegura: {n3 es par y n2 es impar y n1 es impar -> res = 3}
    asegura: {n3 es impar y n2 es impar y n1 es impar -> res = 4}
    }
-}
-- Defino una función para extraer el tercer elemento
thr :: (Int, Int, Int) -> Int
thr (_,_,a) = a

posPrimerPar :: (Int, Int, Int) -> Int
posPrimerPar (x, y, z) | x `mod` 2 == 0 = x
                        | y `mod` 2 == 0 = y
                        | z `mod` 2 == 0 = z
                        | otherwise = 4

prodInt :: (Float,Float) -> (Float,Float) -> Float
prodInt (x,y) (w,z) = x*w + y*z

distanciaPuntos :: (Float,Float) -> (Float,Float) -> Float
distanciaPuntos (x,y) (w,z) = ((x-w)**2 + (y-z)**2)**(1/2)

sumaTerna :: (Float,Float,Float) -> Float
sumaTerna (x,y,z) = x+y+z

multiplo :: Int -> Int -> Bool
multiplo x y = mod x y == 0

{-Completar guia tres para el Jueves
sumarSoloMultiplos :: (Float,Float,Float) -> Int -> Int
sumarSoloMultiplos (x,y,z) n | (multiplo x n)
-}



{-Guía 4: Recursión-}
-- 1) Implementar la funci´on fibonacci:: Integer ->Integer que devuelve el i-´esimo n´umero de Fibonacci
fibonacci :: Int -> Int
fibonacci i | i == 0 = 0
            | i == 1 = 1
            | i >= 2 = fibonacci (i-1) + fibonacci (i-2)

--2) Implementar una funci´on parteEntera :: Float ->Integer
parteEntera :: Float-> Int
parteEntera x | (0 <= x && x < 1) = 0
                | x >= 1 = parteEntera(x-1) + 1
                | otherwise = parteEntera(x+1) - 1

--3) Especificar e implementar la funci´on esDivisible :: Integer ->Integer ->Bool
esDivisible :: Integer -> Integer -> Bool
esDivisible x y | (x - y == 0) && (x > 0 && y > 0) = True
                | (x < y) && (x > 0 && y > 0) = False
                | (x > y) && (x > 0 && y > 0) = esDivisible (x-y) y

--4) Especificar e implementar la funci´on sumaImpares :: Integer ->Integer que dado n ∈ N sume los primeros n numeros impares... ¡SEGUIR TRABAJANDO EN ELLO!
sumaImparesMenores :: Integer -> Integer
sumaImparesMenores x | x == 1 = 1
                    | (x `mod` 2 == 0) = sumaImparesMenores (x-1) 
                    | (x `mod` 2 /= 0 ) = x + sumaImparesMenores (x-2)

sumaImpares :: Integer -> Integer
sumaImpares x = sumaImparesMenores 2*x
                







                




