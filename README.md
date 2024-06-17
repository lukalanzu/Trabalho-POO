Anna Lucia Moro Lanzuolo 13864436
Isabela Guarnier De Mitri 13862264
Nina Ribeiro Silveira 13686751

Requisitos para o Sistema de Ponto de Venda Java Café
Requisitos Funcionais
Os requisitos funcionais definidos para o sistema "Java Café" incluem a implementação de uma interface gráfica de usuário (GUI) intuitiva, funcionalidades de processamento de pedidos e gestão de inventário, relatórios de vendas detalhados, tratamento de exceções personalizadas, armazenamento persistente de dados e uma documentação abrangente.

Interface do Usuário:

Desenvolver uma interface gráfica de usuário (GUI) intuitiva utilizando JavaFX ou Swing.
Incluir botões para adicionar itens a um pedido, completar transações e visualizar inventário e relatórios de vendas.

Processamento de Pedidos:

Permitir aos usuários criar novos pedidos, adicionar itens do menu e finalizar vendas.
Cada pedido deve calcular o custo total, aplicar impostos e gerar um recibo.

Gestão de Inventário:

Implementar um sistema para rastrear os níveis de inventário dos produtos.
Atualizar o inventário automaticamente quando os pedidos forem processados.
Alertar os usuários quando os níveis de inventário estiverem baixos.

Relatórios de Vendas:

Fornecer funcionalidade para visualizar relatórios de vendas diários, semanais e mensais.
Incluir total de vendas, número de transações e itens mais vendidos nos relatórios.

Tratamento de Exceções:

Implementar tratamento robusto de erros para entradas de usuário, gestão de inventário e operações de arquivo.
Implementar exceções personalizadas para cenários específicos de erro, como "Fora de Estoque" ou "Tipo de Pagamento Inválido".

Armazenamento de Dados:

Utilizar arquivos I/O para armazenar e recuperar dados do inventário e registros de vendas.
Garantir a persistência dos dados entre as sessões, salvando as alterações em um arquivo.

Documentação:

Documentar o código de forma completa, especialmente os métodos públicos e interfaces de usuário.
Fornecer um manual do usuário que explique como operar o sistema POS.

Apresentação:

Preparar uma demonstração do sistema POS em funcionamento.
Destacar as principais funcionalidades e discutir os desafios enfrentados durante o desenvolvimento.


Descrição do Projeto:

Neste trabalho utilizamos a linguagem Java para produzir uma GUI e os outros requisitos que o trabalho pede. Para isso criamos 9 classes diferentes:

  CoffeeShopException: Lida com todas as exceções do nosso problema.
  
  EmptyOrderException: Lida com a exceção do pedido vazio, então caso o cliente submeta um pedido vazio aparece uma tela de erro
  .
  InvalidPaymentTypeException: É ativada caso o cliente não selecione nenhum tipo de pagamento.
  
  OutOfStockException: É ativada caso o cliente queira pedir um produto que não tem mais no estoque.
  
  Inventory: É a classe que vai lidar com todos os métodos relacionados ao inventário, atualizando-o a cada compra.
  
  MyGraphics: É a classe que vai criar a logo para o café e colocá-la na GUI.
  
  ProductPanel: É onde é criado o painel de produtos, adicionando na GUI o nome de cada produto, o botão de "+" e "-" e o número de selecionados. Essa classe também cria os métodos de atualização do pedido completo, atualização do pedido de comida e do de bebida.
  
  Sale: Essa classe calcula o total da venda, o total de produtos vendidos e o produto mais vendido.
  
  CoffeeShopGUI: É a classe que vai conter todas as informações da interface gráfica, incluindo os painéis necessários, os botões e todos os métodos necessários para a funcionalidade correta dos botões.
  

Planejamento dos Testes:

No código há diversas ações que podem ser feitas para testar a sua funcionalidade. Podemos iniciar com o teste das exceções, tentando comprar mais itens do que o estoque disponível, tentar pagar sem escolher o tipo de pagamento e pagar uma compra vazia. Outros testes incluem comprar mais de um item na mesma compra, incluindo bebida e comida (separados em diferentes vetores no código).

Para testar os botões, clique em cada um para verificar que, quando nenhum item foi adicionado ao carrinho, ao clicar em "-" o valor de itens não muda a menos que se clique em "+". Teste todos os outros botões para ver que cada um responde da maneira correta conforme o clique e estágio da compra. Além disso, é interessante verificar a criação do documento que armazena o histórico de compras, testando a funcionalidade fazendo uma ou algumas compras e clicando nos botões de Relatório.

Realizamos também alguns testes com o JUnit, como o teste "testIsOrderEmpty()", que verifica o comportamento do método isOrderEmpty() da classe CoffeeShopGUI em duas situações: ordem vazia inicialmente e ordem não vazia após adicionar um item.

Outro teste foi o "testResetOrder", que verifica se o método resetOrder() da classe CoffeeShopGUI está resetando corretamente o estado da ordem e outros componentes relacionados após ser chamado.

O teste "testShowOrderValid" testa a funcionalidade de showOrder para uma ordem válida e verifica se o estoque é reduzido corretamente.

O teste "testShowOrderWithFoodAndDrink" seleciona tanto comidas quanto bebidas com quantidades válidas, verifica se o estoque é reduzido de acordo com a quantidade selecionada de comida e bebida e verifica se o texto do pedido contém os itens escolhidos.

Por fim, o teste para verificar a exceção de item fora do estoque simula uma compra com itens a mais do que o estoque disponível e verifica se a exceção foi lançada corretamente.


Como Rodar o Código:

Para rodar nosso código é necessário, primeiramente, baixar o JDK (Java Development Kit) no site da Oracle. Execute o instalador que você baixou, siga as instruções de instalação e aceite as configurações padrão.

Depois, acesse pelo terminal o diretório correto, que é o caminho para o arquivo (ou abra a pasta do projeto diretamente pelo editor que você está usando, como IntelliJ).

Se estiver no terminal, rode o comando "javac CoffeeShopGUI.java" para compilar o código, e depois "java CoffeeShopGUI" para executar. Em um editor apropriado para Java, haverá um botão para rodar o projeto.

Problemas:

Tivemos problemas na criação dos arquivos para os relatórios de vendas, na parte de divisão de strings utilizando a função split, já conhecida pelo Java. Para dividir caracteres especiais não tínhamos conhecimento que era necessário o uso de duas barras invertidas para a divisão correta da string. Além disso, tivemos dificuldade também no método de atualizar o inventário após cada compra, pois o botão de adicionar itens estava interligado, diminuindo a mesma quantidade de estoque para todos os itens.

Comentários:

Foi um projeto complicado de fazer mas divertido. Aprendemos a lidar com interface gráfica, múltiplas classes para lidar com as restrições do problema, como por exemplo o estoque, o número de vendas e a logo do café. Além disso, também aplicamos o conceito de testes utilizando JUnit.
