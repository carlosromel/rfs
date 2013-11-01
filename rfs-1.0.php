<?php
/**
 * @author    Carlos Romel <carlos.romel@gmail.com>
 * @license   http://opensource.org/licenses/gpl-license.php GNU Public License
 */

/**
 * Generates an UUID
 * 
 * @author     Anis uddin Ahmad <admin@ajaxray.com>
 * @link       http://www.ajaxray.com/blog/2008/02/06/php-uuid-generator-function/
 * @param      string  an optional prefix
 * @return     string  the formatted uuid
 */
function uuid($prefix = '') {
    $chars = md5(uniqid(mt_rand(), true));
    $uuid  = substr($chars,  0, 8);
    $uuid .= substr($chars,  8, 4) ;
    $uuid .= substr($chars, 12, 4);
    $uuid .= substr($chars, 16, 4);
    $uuid .= substr($chars, 20, 12);

    return $prefix . $uuid;
}

$root = "ProvaConceito";

if (! file_exists($root)) {
    mkdir($root);
}

date_default_timezone_set("UTC");

$inicio = time();

/*
 * Criação de 1.048.576 arquivos (1MB).
 */
for ($n = 0; $n < 1024 * 1024; ++$n) {
    $uuid    = uuid();
    $caminho = $root;

    /*
     * Para diminuir a quantidade de diretórios criados e consequentemente
     * aumentar a quantidade de arquivos por diretório final, podemos
     * restringir a quantidade máxima de níveis criados, incrementando a
     * variável abaixo.
     */
    $redutor = 0;

    for ($p = 0; $p < (strlen($uuid) - $redutor); $p += 2) {
        $caminho .= "/" . substr($uuid, $p, 2);
        if (! file_exists($caminho)) {
            mkdir ($caminho);
        }
    }

    file_put_contents("$caminho/$uuid", $uuid);

    echo "[$uuid]\n";
}

$termino = time();
$duracao = $termino - $inicio;

printf("Início: %s, término: %s. Duração: %s\n",
       date("H:i:s", $inicio),
       date("H:i:s", $termino),
       date("H:i:s", $duracao));

?>
