package app;

import java.awt.image.BufferedImage;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BufferedImage imagem = ManipulaImagem.abrirImagem("C:\\Users\\Daniel\\IdeaProjects\\Projeto\\src\\app\\paisagem.jpg");
        ManipulaImagem.exibirImagem(imagem, FiltrosPontuais.binarizacao(imagem, "red" ));
    }
}