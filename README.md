# Market Api

 A shop cart API following an iFood dev week.



## Technologies used:

1. Java 17

2. H2 database

3. Spring Web

4. Spring Data

5. Lombok

6. Gradle



## Class diagram

```mermaid
classDiagram
Cliente --|> Sacola
Cliente : private Long id
Cliente : private String nome
Cliente : private Endereco endereco

Sacola : private Long id
Sacola : private Cliente cliente
Sacola : private List<Item> itensSacola
Sacola : private double valorTotalSacola
Sacola : private FormaPagamento formaPagamento
Sacola : private boolean fechada

Endereco --|> Cliente
Endereco : private String cep
Endereco : private String complemento

FormaPagamento --|> Sacola
FormaPagamento : String DINHEIRO
FormaPagamento : String MAQUINETA

Endereco --|> Restaurante
Restaurante --|> Produto
Restaurante : private Long id
Restaurante : private String nome
Restaurante : private List<Produtos> cardapio
Restaurante : private Endereco endereco

Sacola --|> Item
Produto --|> Item
Item : private Long id
Item : private Produto produto
Item : private int quantidade
Item : private Sacola sacola

Produto : private Long id
Produto : private String nome
Produto : private double valorUnitario
Produto : private Boolean disponivel
Produto : private Restaurante restaurante
 
```
