<div id="top"></div>
<br />

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Índice</summary>
  <ol>
    <li><a href="#sobre-o-projeto">Sobre o projeto</a></li>
    <li><a href="#tecnologias-utilizadas">Tecnologias utilizadas</a></li>
    <li><a href="#requisitos-necessários">Requisitos necessários</a></li>
    <li><a href="#como-utilizar">Como utilizar</a></li>
    <li><a href="#documentação-da-api">Documentação da api</a></li>
    <li><a href="#acesso-a-interface-do-banco-h2">Acesso a interface do banco H2</a></li>
    <li><a href="#licença">Licença</a></li>
    <li><a href="#contato">Contato</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## Sobre o projeto

[![Take Me Home][product-screenshot]](https://github.com/Barros99/)

Esse projeto é um desafio final para a acelaração em Java, fornecido pela [Trybe](https://www.betrybe.com/) em parceria com a [CI&T](https://ciandt.com/br/)

Basicamente é um backend RESTful, que fornece um CRUD para os seguintes endpoints:

- /v1/animals

<p align="right">(<a href="#top">back to top</a>)</p>

## Tecnologias utilizadas

Estas foram algumas das tecnologias utilizadas neste projeto:

- [Java](https://www.java.com/pt-BR/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Docker](https://www.docker.com/)
- [H2](https://dbdb.io/db/h2/)

<p align="right">(<a href="#top">back to top</a>)</p>

## Requisitos Necessários

[ OBSERVAÇÃO ] - Para poder rodar a aplicação é necessário ter o [Docker](https://docs.docker.com/) instalado na máquina.

Site do docker

- https://docs.docker.com/engine/install/

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- GETTING STARTED -->

## Como utilizar

Siga os passos abaixo para rodar a aplicação:

1. Clone o repositório
   ```sh
   git clone git@github.com:Barros99/takemehome.git
   ```
2. Entre na pasta raiz do projeto
   ```sh
   cd takemehome
   ```
3. Digite o seguinte comando para inicializar a aplicação
   ```js
   docker-compose up -d
   ```
4. Para acessar a api, copie e cole o endereço abaixo no seu navegador
   ```js
   http://localhost:8080/v1/animals
   ```

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- DOCUMENTATION -->

## Documentação da API

A documentação pode ser acessada no endereço abaixo:

Formato padrão:

```js
  http://localhost:8080/swagger-ui.html
```

Formato json:

```js
  http://localhost:8080/api-docs
```

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- DATABASE -->

## Acesso a interface do banco H2

O banco de dados em memória H2, pode ser acessado em:

```js
 http://localhost:8080/h2-console
```

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- LICENSE -->

## Licença

Distribuído sob a licença MIT.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTACT -->

## Contato

Project Link: [https://github.com/Barros99/takemehome](https://github.com/Barros99/takemehome)

Jean Barros - [linkedIn.com/in/j3anbarros/](https://www.linkedin.com/in/j3anbarros/)

<p align="right">(<a href="#top">back to top</a>)</p>

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/j3anbarros/
[product-screenshot]: src/assests/mern.png
