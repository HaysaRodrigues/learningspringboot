# learning spring boot


### Annotations

- `@ResponseBody`: para o spring entender que tem que retornar um response e não uma página
- `@RestController`: essa annotation é colocada em cada controller, pra gente não precisar colocar a annotation @ResponseBody. Quando você usa ela dentro do controller, o spring entender que todos os métodos vão retornar um response. 
- `@RequestMapping("/endpoint")`: onde você coloca o endpoint
- `@Value`: atribuir um valor da anotation para a variável. Você pode fazer um ternário dentro dela com um valor default també, caso o valor do arquivo de fora não funcione: *@Value("#{systemProperties['unknown'] ?: 'some default'}")*
- 
