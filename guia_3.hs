-- Guia 3: Funciones en Haskell

-- Ejercicio 1. a) Implentar la función parcial f :: Integer ->Integer definida por extensión de la siguiente manera:
f :: Int -> Int
f 1 = 8
f 4 = 131
f 16 = 16

-- b) Análogamente, especificar e implementar la función parcial g :: Integer ->Integer
g :: Int -> Int
g 8 = 16
g 16 = 4
g 131 = 1

-- Identidades:
h :: Int -> Int
h x = f (g x)

k :: Int -> Int
k x = g (f x)

-- Ejercicio 2

absoluto :: Int -> Int
absoluto x | x < 0 = -x
            | otherwise = x

maximoabsoluto :: Int -> Int -> Int
maximoabsoluto x y | absoluto x > absoluto y = absoluto x
                    | absoluto x < absoluto y = absoluto y

maximo3 :: Int -> Int -> Int -> Int
maximo3 x y z | x > y && x > z = x
                | y > z && y > x = y
                | z > x && z > y = z

algunoES0 :: Float -> Float -> Bool
algunoES0 x y   | x == 0 || y == 0 = True
                | otherwise = False

algunoES0_pattern_matching :: Float -> Float -> Bool
algunoES0_pattern_matching _ 0 = True
algunoES0_pattern_matching 0 _ = True
algunoES0_pattern_matching _ _ = False

ambosSon0 :: Float -> Float -> Bool
ambosSon0 x y   | x == 0 && y == 0 = True
                | otherwise = False

ambosSon0_pattern_matching :: Float -> Float -> Bool
ambosSon0_pattern_matching 0 0 = True
ambosSon0_pattern_matching _ _ = False   

{- f mismoIntervalo: dados dos números reales, indica si están relacionados considerando la relación de equivalencia en R
cuyas clases de equivalencia son: (−∞, 3], (3, 7] y (7, ∞), o dicho de otra forma, si pertenecen al mismo intervalo. -}
mismoIntervalo :: Float -> Float -> Bool
mismoIntervalo x y  | x <= 3 && y <= 3 = True
                    | (3 < x && x <= 7) && (3 < y && y <= 7) = True
                    | x >= 7 && y >= 7 = True

sumaDistintos :: Int -> Int -> Int -> Int
sumaDistintos x y z | x == y && x /= z = x + z
                    | x == z && x /= y = x + y
                    | y == z && y /= x = x + y
                    | y == x && x == z = x

esMultiploDe :: Int -> Int -> Bool
esMultiploDe x y    | x `mod` y == 0 = True
                    | otherwise = False

digitoUnidades :: Int -> Int
digitoUnidades x = mod x 10

digitoDecenas :: Int -> Int
digitoDecenas x = (x `div` 10) `mod` 10



-- Ejercicio 4

prodInt :: (Float, Float) -> (Float, Float) -> Float
prodInt (a, b) (c, d) = a*c + b*d

todoMenor :: (Float, Float) -> (Float, Float) -> Bool
todoMenor x y   | fst x < fst y && snd x < snd y = True
                | otherwise = False

distanciaPuntos :: (Float, Float) -> (Float, Float) -> Float
distanciaPuntos (x,y) (w,z) = ((x-w)^2 + (y-z)^2)^1/2

sumaTerna :: (Int, Int, Int) -> Int
sumaTerna (x,y,z) = x+y+z

sumarSoloMultiplos :: (Int, Int, Int) -> Int -> Int
sumarSoloMultiplos (x1,x2,x3) y | x1 `mod` y == 0 && x2 `mod` y == 0 && x3 `mod` y == 0 = x1+x2+x3
                                | x1 `mod` y == 0 && x2 `mod` y == 0 = x1+x2
                                | x1 `mod` y == 0 && x3 `mod` y == 0 = x1 + x3
                                | x2 `mod` y == 0 && x3 `mod` y == 0 = x2+x3
                                | x1 `mod` y == 0 = x1
                                | x2 `mod` y == 0 = x2
                                | x3 `mod` y == 0 = x3

posPrimerPar :: (Int,Int,Int) -> Int
posPrimerPar (x,y,z)    | x `mod` 2 == 0 = 1
                        | y `mod` 2 == 0 = 2
                        | z `mod` 2 == 0 = 3
                        | otherwise = 4

crearPar :: ta -> tb -> (ta,tb)
crearPar a b = (a,b)

invertir :: (ta,tb) -> (tb,ta)
invertir (a,b) = (b,a)
