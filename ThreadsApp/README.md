### ThreadsApp

Este projeto apresenta diversas maneiras de executar uma tarefa pesada (como carregar uma imagem, 
fazer uma conexão de rede, reproduzir um vídeo, etc) em um aplicativo Android. O projeto contém 7 telas, 
além da tela principal, cada uma apresentando uma forma de executar uma tarefa pesada. As activities 
são apresentadas abaixo:

- NoThreadActivity - mostra a execução da tarefa pesada na própria activity. O aplicativo para de responder a 
interaçes com o usuário durante a execução. Este método não é recomendado.

- ThreadSimplesActivity - tenta executar o código da tarefa pesada em uma thread separada. Não funciona pois o 
aplicativo termina com um erro quando a thread tenta alterar a interface do usuário. No Android, apenas a thread
que na qual a activity é executada (UI Thread) pode configurar os componentes da tela.

- ViewPostActivity - executa a tarefa em uma thread separada mas usa o método post() de uma view para executar 
o código que toca a view.

- AsyncTaskActivity - utiliza uma AsyncTask para executar a tarefa pesada.

- HandlerPostActivity - utiliza um handler para executar a tarefa. A tarefa a ser executada é passada ao handler
usando o seu método post(). Similar á solução apresentada em ViewPostActivity;

- HandlerMessageActivity - utiliza um handler para executar a tarefa mas não utiliza o método post() para enviar 
a tarefa ao handler. Envia mensagens ao handler através do método sendMessage().

- EventBusActivity - executa a tarefa pesada em uma thread separada e a biblioteca EventBus para trocar mensagens entre
a view e a thread.

