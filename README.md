# ED2-Projeto1
Repositorio para o projeto 1 de Estrutura de Dados 2
1 Árvore da Família
O Problema
Você foi contratado para desenvolver um programa que determina se existe algum parentesco entre duas pessoas e, caso exista, qual é o grau de parentesco.
Este problema envolve a aplicação da estrutura de uma árvore para determinar o grau de
parentesco que existe entre pares de pessoas que podem fazer parte de uma mesma “árvore da
família”.
As informações de entrada para o seu programa consistem de um arquivo contendo uma
relação (seqüência) de pares de nomes (filho-pai), onde um par consiste do nome do filho seguido pelo nome do pai (único). A partir destas informações, o programa deve construir a
árvore da família, possibilitando consultas de parentesco, que também podem ser expressas
pelo par de nomes das pessoas pelas quais desejo pesquisar.
Se existe algum parentesco entre os nomes informados na consulta, o programa deve determinar qual o grau de parentesco. Lembre-se de que a árvore genealógica, neste caso, é uma
árvore simples, ou seja, cada filho possui um pai único.
Para isto, considere que o par filho-pai p q denota que p é o filho de q. Para determinar o
relacionamento entre os nomes, utilizamos as seguintes definições:
• p é um descendente-0 de q se, e somente se, o par filho-pai p q aparece na relação de
pares de nomes do arquivo de entrada;
• p é um descendente-k de q se, e somente se, o par filho-pai p r aparece na seqüência na
relação de pares de nomes do arquivo de entrada e r é um descendente-(k − 1) de q.
Para facilitar a saída do programa, o relacionamento entre uma p e uma pessoa q deve ser
expresso por uma das quatro seguintes relações:
1. filho — neto, bisneto, tataraneto, tatataraneto, etc.
Por definição p é o “filho” de q se, e somente se, o par p q aparece na relação de pares de
nomes filho-pai do arquivo de entrada (i.e., p é um descendente-0 de q); p é o “neto” de q
se, e somente se, p é um descendente-1 de q; e
p é o “ tata. . .
| {z }
n vezes
raneto” de q
se, e somente se p é um descendente-(n + 1) de q.
2. pai — avô, bisavô, tataravô, tatataravô, etc.
Por definição p é o “pai” de q se, e somente se, o par q p aparece na relação de pares de
nomes filho-pai do arquivo de entrada (i.e., p é um ancestral-0 de q); p é o “avô” de q se,e somente se, p é um ancestral-1 de q; e
p é o “ tata. . .
| {z }
n vezes
ravô” de q
se, e somente se, p é um ancestral-(n + 1) de q.
3. primo — primos podem ser primos em grau 0 (irmãos), primos em grau 1, primos em
grau 2, primos em grau 3, etc.
Por definição p e q são “primos” se, e somente se eles estão relacionados na árvore da
família. Para entendermos esta relação, seja r o representante do ancestral mais comum
de p e q (i.e., não existe nenhum descendente de r que seja, ao mesmo tempo, um ancestral
de p e q), onde p é um descendente-m de r e q é um descendente-n de r.
Então, pode definição, os primos p e q são primos-“k
◦” se, e somente se, k = min(n, m),
e, também por definição, p e q são “primos em grau j” se, e somente se, j = | n − m |.
4. irmão — primo-0
◦
em grau 0 são “irmãos” (possuem o mesmo pai).
