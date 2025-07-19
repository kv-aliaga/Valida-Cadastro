
<?php
// Capturando valores retroalimentados
$nome = $_POST['nome'] ?? '';
$email = $_POST['email'] ?? '';
$nascimento = $_POST['nascimento'] ?? '';
$cargo = $_POST['cargo'] ?? '';
$empresa = $_POST['empresa'] ?? '';
$cadastro = date('Y-m-d H:i:s');
$mensagem = '';

// Processa o formulário antes do HTML
if ($_SERVER['REQUEST_METHOD'] === 'POST' && !empty($nome) && !empty($email) && !empty($empresa)) {
    $host = 'localhost';
    $database = 'involucre';
    $user = 'postgres';
    $pass = '102023Va';
    $port = '5432';
    $charset = 'utf8';

    $dsn = "pgsql:host=$host;port=$port;dbname=$database";

    $options = [
        PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION, // Erros lançam exceção (mais fácil de depurar)
        PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,       // Retorna arrays associativos por padrão
        PDO::ATTR_EMULATE_PREPARES   => false,                  // Usa prepares nativos do PostgreSQL (mais seguro e mais rápido)
    ];

    try {
        $pdo = new PDO($dsn, $user, $pass, $options);
        $sql = "INSERT INTO login_site (nome, email, nascimento, cargo, empresa, cadastro) VALUES (?, ?, ?, ?, ?, ?)";
        $stmt = $pdo->prepare($sql);
        $stmt->execute([$nome, $email, $nascimento, $cargo, $empresa, $cadastro]);
        $mensagem = "Usuário cadastrado com sucesso!";
    } catch (PDOException $e) {
        $mensagem = "Erro de conexão: " . $e->getMessage();
        // Para debug detalhado, descomente a linha abaixo:
        // var_dump($e->getMessage());
    }
}
?>

<!DOCTYPE html>
<html lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Usuário</title>
    <link rel="stylesheet" href="style.css">
    <style>
        
body {
    font-family: 'Segoe UI', Arial, Helvetica, sans-serif;
    background: linear-gradient(120deg, #56ab2f 0%, #a8e063 100%);
    min-height: 100vh;
    margin: 0;
    display: flex;
    align-items: center;
    justify-content: center;
}

main {
    background: #fff;
    padding: 2.5rem 2rem 2rem 2rem;
    border-radius: 18px;
    box-shadow: 0 4px 24px rgba(0,0,0,0.13);
    min-width: 340px;
    max-width: 400px;
    width: 100%;
}

h1 {
    text-align: center;
    color: #388e3c;
    margin-bottom: 1.5rem;
    font-size: 2rem;
    letter-spacing: 1px;
}

form {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

label {
    font-size: 1rem;
    color: #333;
    margin-bottom: 0.2rem;
    font-weight: 500;
}

input[type="text"],
input[type="email"],
input[type="date"] {
    font-size: 1rem;
    padding: 0.6rem 0.8rem;
    border: 1px solid #bdbdbd;
    border-radius: 8px;
    outline: none;
    transition: border-color 0.2s;
    background: #f8f8f8;
}

input[type="text"]:focus,
input[type="email"]:focus,
input[type="date"]:focus {
    border-color: #56ab2f;
    background: #fff;
}

input[type="submit"] {
    background: linear-gradient(90deg, #56ab2f 0%, #388e3c 100%);
    color: #fff;
    font-size: 1.1rem;
    font-weight: bold;
    border: none;
    border-radius: 8px;
    padding: 0.7rem 0;
    margin-top: 0.5rem;
    cursor: pointer;
    box-shadow: 0 2px 8px rgba(56,142,60,0.08);
    transition: background 0.2s, transform 0.1s;
}

input[type="submit"]:hover {
    background: linear-gradient(90deg, #388e3c 0%, #56ab2f 100%);
    transform: translateY(-2px) scale(1.03);
}

p {
    text-align: center;
    margin-top: 1.2rem;
    font-size: 1.08rem;
    color: #2e7d32;
    font-weight: 500;
    min-height: 1.5em;
}

@media (max-width: 480px) {
    main {
        min-width: unset;
        padding: 1.2rem 0.5rem 1.5rem 0.5rem;
    }
    h1 {
        font-size: 1.3rem;
    }
}
    </style>
</head>
<body>

<main>
    <h1>Faça seu Cadastro!</h1>
    <form action="<?= $_SERVER['PHP_SELF'] ?>" method="post" autocomplete="off">
        <label for="nome">Nome</label>
        <input type="text" name="nome" id="nome" value="<?= htmlspecialchars($nome) ?>" placeholder="Digite seu nome completo" required>

        <label for="email">Email</label>
        <input type="email" name="email" id="email" value="<?= htmlspecialchars($email) ?>" placeholder="exemplo@email.com" required>

        <label for="nascimento">Nascimento</label>
        <input type="date" name="nascimento" id="nascimento" value="<?= htmlspecialchars($nascimento) ?>" placeholder="dd/mm/aaaa">

        <label for="cargo">Cargo</label>
        <input type="text" name="cargo" id="cargo" value="<?= htmlspecialchars($cargo) ?>" placeholder="Seu cargo na empresa">

        <label for="empresa">Empresa</label>
        <input type="text" name="empresa" id="empresa" value="<?= htmlspecialchars($empresa) ?>" placeholder="Nome da empresa" required>

        <input type="submit" value="Enviar Dados">
    </form>
    <div style="min-height:1.5em;">
        <p><?= $mensagem ?></p>
    </div>
</main>

</body>
</html>