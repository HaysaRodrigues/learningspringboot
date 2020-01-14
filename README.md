# learning spring boot

Por que spring boot? `By now, you're hopefully noticing a pattern – most Spring libraries are easily imported into our project with the use of simple Boot starters.`



### [Iniciar um projeto spring](https://start.spring.io/)

### Annotations

- `@SpringBootApplication` annotation de configuração, onde usamos na classe que inicia a aplicação. Equivalente à `@Configuration`.
- `@SpringBootTest`: carrega a aplicação e verifica se não existem erros ao iniciar a aplicação.

- `@ResponseBody`: para o spring entender que tem que retornar um response e não uma página
- `@RestController`: essa annotation é colocada em cada controller, pra gente não precisar colocar a annotation @ResponseBody. Quando você usa essa annotation dentro do controller, o spring entende que todos os métodos vão retornar um response.
- `@Controller`: usada quando o controller não vai retornar HTTP response
- `@RequestMapping("/endpoint")`: onde você especifica o caminho do endpoint
- `@Value`: atribuir um valor da anotation para a variável. Você pode fazer um ternário dentro dela com um valor default també, caso o valor do arquivo de fora não funcione: ```@Value("#{systemProperties['unknown'] ?: 'some default'}")```
- `@EnableJpaRepositories`: vai buscar por repositórios no nosso pacote
- `@EntityScan`: vai buscar por entidades JPA dentro do pacote
- `@ControllerAdvice`: Quando usada em uma classe que herda de ResponseEntityExceptionHandler, essa annotation faz com que todos os controllers (@Controller e @RestController) enxerguem aquela classe de exceptions.
- `@ExceptionHandler`: essa annotation tem que estar em todos os métodos que representam uma exception. No caso, ela lida com as exceptions, como o nome sugere.


### resources/application.properties

- [Nessa página da documentação](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html) têm várias properties que podemos usar nesse arquivo para diversas configurações.

### Boas práticas 
- Usar o padrão de projetos DTO 
- Usar o padrão de projetos Repository (classes de persistência de dados)

### Geral

- Nas classes que o spring gerencia, normalmente colocamos alguma annotation, no caso de uma interface não é necessário, o spring já entende a classe de interface como interface.
- No arquivo `application.properties`, devem ser declaradas as configurações da aplicação, inclusive as relacionadas ao banco de dados dela.

### Onde buscar bons materiais 

- https://www.baeldung.com/spring-boot-start
