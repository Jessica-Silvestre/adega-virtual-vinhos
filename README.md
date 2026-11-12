# Adega Virtual – CRUD de usuários e cadastro de vinhos

**Aluno(a):** Jéssica Silvestre de Paula

> O **módulo de vinhos não faz parte do material da aula nem do que foi pedido na P2**.
> Foi desenvolvido por opção
> gostar do tema, para aplicar em um novo cadastro o que foi aprendido no CRUD
> de usuários.

## Relação com o projeto de login

O CRUD de usuários pedido na P2 (login, cadastro, menu, alteração e exclusão)
está entregue no repositório
[login-P2](https://github.com/Jessica-Silvestre/login-P2). Este repositório
reúne o mesmo fluxo com um visual de adega e acrescenta o módulo de vinhos.

## Fluxo do sistema

Login → (Cadastrar) → Menu → **Meus Vinhos** / Alterar Dados / Excluir / Voltar

## Módulo de vinhos

### O que faz
- Cadastra vinhos (nome, tipo, país de origem, ano da safra, preço e quantidade).
- Lista os vinhos cadastrados em tabela, em ordem alfabética.
- Exclui o vinho selecionado, com confirmação.
- Mostra o total de rótulos cadastrados e de garrafas em estoque.

### O que foi aplicado do que foi visto em aula

| Conceito da aula | Como foi usado nos vinhos |
|---|---|
| Classe com atributos privados, getters e setters | `Vinho` segue o modelo da classe `Usuario` |
| Classe `Conexao` | Reutilizada nos métodos de banco |
| Métodos de CRUD na classe de domínio | `cadastraVinho` (como `cadastraUsuario`), `excluiVinho` (como `excluiUsuario`) e `existeVinho` (como `verificaUsuario`, evita duplicidade) |
| `try/catch` com exceções | Todo acesso ao banco é protegido |
| Tela com `JFrame`, `JLabel`, `JTextField` e `JButton` | `TelaVinhos` é montada no construtor, como `TelaLogin` |
| Eventos com `ActionListener` | Botões Cadastrar, Limpar, Excluir e Voltar |
| Mensagens com `JOptionPane` | Avisos de erro, sucesso e confirmação |
| Validação antes de gravar | Campos vazios e duplicidade são verificados antes do `INSERT` |
| Navegação entre telas (`new Tela()`, `setVisible`, `dispose`) | Menu ⇄ Meus Vinhos |
| Atributo `static` | O nome do usuário logado aparece no Menu |

### Validações

- Nome e país obrigatórios.
- Ano numérico, entre 1800 e 2100.
- Preço numérico e não negativo.
- Quantidade numérica e não negativa.
- Não permite dois vinhos com o mesmo nome.
- Exclusão exige uma linha selecionada e confirmação.

## Como executar

1. Execute o `script.sql` no MySQL Workbench.
2. Abra a pasta do projeto no NetBeans (*File > Open Project*).
3. O driver MySQL já está na pasta `lib` e referenciado no projeto. Se aparecer aviso em **Libraries**, clique com o botão direito em *Libraries > Add JAR/Folder* e selecione `lib/mysql-connector-j-8.4.0.jar`.
4. Confira em `Conexao.java` o usuário, a senha e a porta do seu MySQL.
5. Execute `TestaTela.java` (botão direito > *Run File*).

**Usuário de teste:** `admin` / `123`
