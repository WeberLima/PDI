package app;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FiltrosPontuais {


    private static int validaLimitesRGB(int valor, int acrescimo){
        int soma = valor + acrescimo;
        if(soma > 255) soma = 255;
        if(soma < 0) soma = 0;
        return soma;
    }

    public static BufferedImage bandaR(BufferedImage imgEntrada) {
        int width = imgEntrada.getWidth();
        int height = imgEntrada.getHeight();
        BufferedImage imgSaida = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_RGB);

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Color cor = new Color(imgEntrada.getRGB(w, h));
                Color novaCor = new Color (cor.getRed(), 0 ,0);
                imgSaida.setRGB(w, h, novaCor.getRGB()); //desenhando os pixels da imagem
            }
        }
        return imgSaida;
    }

    public static BufferedImage bandaG(BufferedImage imgEntrada) {
        int width = imgEntrada.getWidth();
        int height = imgEntrada.getHeight();
        BufferedImage imgSaida = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_RGB);

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Color cor = new Color(imgEntrada.getRGB(w, h));
                Color novaCor = new Color (0, cor.getGreen() ,0);
                imgSaida.setRGB(w, h, novaCor.getRGB()); //desenhando os pixels da imagem
            }
        }
        return imgSaida;
    }

    public static BufferedImage bandaB(BufferedImage imgEntrada) {
        int width = imgEntrada.getWidth();
        int height = imgEntrada.getHeight();
        BufferedImage imgSaida = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_RGB);

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Color cor = new Color(imgEntrada.getRGB(w, h));
                Color novaCor = new Color (0, 0 ,cor.getBlue());
                imgSaida.setRGB(w, h, novaCor.getRGB()); //desenhando os pixels da imagem
            }
        }
        return imgSaida;
    }

    public static BufferedImage aumentoBrilho(BufferedImage imgEntrada, int aumento) {
        int width = imgEntrada.getWidth();
        int height = imgEntrada.getHeight();
        BufferedImage imgSaida = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_RGB);

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Color cor = new Color(imgEntrada.getRGB(w, h));
                int red = cor.getRed() + aumento;
                int green = cor.getGreen() + aumento;
                int blue = cor.getBlue() + aumento;

                // Garante que os valores RGB permaneçam dentro do intervalo [0, 255]
                red = Math.min(Math.max(red, 0), 255);
                green = Math.min(Math.max(green, 0), 255);
                blue = Math.min(Math.max(blue, 0), 255);

                Color novaCor = new Color(red, green, blue);
                imgSaida.setRGB(w, h, novaCor.getRGB());
            }
        }
        return imgSaida;
    }


    public static BufferedImage multiBrilho(BufferedImage imgEntrada, float mult) {
        int width = imgEntrada.getWidth();
        int height = imgEntrada.getHeight();
        BufferedImage imgSaida = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_RGB);

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Color cor = new Color(imgEntrada.getRGB(w, h));
                int red = (int) (cor.getRed() * mult);
                int green = (int) (cor.getGreen() * mult);
                int blue = (int) (cor.getBlue() * mult);

                // Garante que os valores RGB permaneçam dentro do intervalo [0, 255]
                red = Math.min(Math.max(red, 0), 255);
                green = Math.min(Math.max(green, 0), 255);
                blue = Math.min(Math.max(blue, 0), 255);

                Color novaCor = new Color(red, green, blue);
                imgSaida.setRGB(w, h, novaCor.getRGB());
            }
        }
        return imgSaida;
    }

    public static BufferedImage aumentoTonalidade(BufferedImage imgEntrada, String tipo, int aumento) {
        String[] possibilidades = {"red", "green", "blue"};
        boolean tipoValido = false;
        for (String possibilidade : possibilidades) {
            if (tipo.equalsIgnoreCase(possibilidade)) {
                tipoValido = true;
                break;
            }
        }

        if (!tipoValido) {
            throw new RuntimeException("Tipo de Cinza Inválido");
        }

        int width = imgEntrada.getWidth();
        int height = imgEntrada.getHeight();
        BufferedImage imgSaida = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Color cor = new Color(imgEntrada.getRGB(w, h));
                int red = cor.getRed();
                int green = cor.getGreen();
                int blue = cor.getBlue();

                Color novaCor = new Color(0, 0, 0);
                if (tipo.equalsIgnoreCase("red")) {
                    novaCor = new Color(Math.min(red + aumento, 255), blue, green);
                } else if (tipo.equalsIgnoreCase("green")) {
                    novaCor = new Color(red, Math.min(green + aumento, 255), blue);
                } else if (tipo.equalsIgnoreCase("blue")) {
                    novaCor = new Color(red, green, Math.min(blue + aumento, 255));
                } else {
                    System.out.println("Tipo de Banda Inválida");
                }
                imgSaida.setRGB(w, h, novaCor.getRGB());
            }
        }
        return imgSaida;
    }

    public static BufferedImage cinza(BufferedImage imgEntrada, String tipo) {
        if(imgEntrada == null) throw  new RuntimeException("Imagem Nula");

        String[] possibilidades = {"red", "green", "blue", "média"};
        for (String possibilidade : possibilidades){
            if(tipo.equalsIgnoreCase(possibilidade)) {
                break;
            }
            new RuntimeException("Tipo de cinza inválido");

        }


        int width = imgEntrada.getWidth();
        int height = imgEntrada.getHeight();
        BufferedImage imgSaida = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_RGB);

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Color cor = new Color(imgEntrada.getRGB(w, h));
                int red = cor.getRed();
                int green = cor.getGreen();
                int blue = cor.getBlue();

                Color novaCor = new Color(0,0,0);
                int media = 0;
                if (tipo.equalsIgnoreCase("red"))
                    novaCor = new Color(red, red, red);
                else if (tipo.equalsIgnoreCase("green"))
                    novaCor = new Color(green, green, green);
                else if (tipo.equalsIgnoreCase("blue"))
                    novaCor = new Color(blue, blue, blue);
                else if (tipo.equalsIgnoreCase("média")) {
                    media = (red + green + blue) / 3;
                    novaCor = new Color(media, media, media);
                }
                imgSaida.setRGB(w, h, novaCor.getRGB());
            }
        }
        return imgSaida;
    }

    public static BufferedImage binarizacao(BufferedImage imgEntrada, String tipo) {
        if(imgEntrada == null) throw  new RuntimeException("Imagem Nula");
        int bin=127;
        String[] possibilidades = {"red", "green", "blue", "média"};
        for (String possibilidade : possibilidades){
            if(tipo.equalsIgnoreCase(possibilidade)) {
                break;
            }
            new RuntimeException("Tipo de cinza inválido");

        }


        int width = imgEntrada.getWidth();
        int height = imgEntrada.getHeight();
        BufferedImage imgSaida = new BufferedImage(
                width,
                height,
                BufferedImage.TYPE_INT_RGB);

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                Color cor = new Color(imgEntrada.getRGB(w, h));
                int red = cor.getRed();
                int green = cor.getGreen();
                int blue = cor.getBlue();

                Color novaCor = new Color(0,0,0);
                int media = 0;
                if (tipo.equalsIgnoreCase("red"))
                    novaCor = new Color(red, red, red);
                else if (tipo.equalsIgnoreCase("green"))
                    novaCor = new Color(green, green, green);
                else if (tipo.equalsIgnoreCase("blue"))
                    novaCor = new Color(blue, blue, blue);
                else if (tipo.equalsIgnoreCase("média")) {
                    media = (red + green + blue) / 3;
                    novaCor = new Color(media, media, media);
                    if (media <= bin){
                        imgSaida.setRGB(w, h, Color.BLACK.getRGB());

                    }else if(media > bin){
                        imgSaida.setRGB(w, h, Color.white.getRGB());
                    }
                }
            }
        }
        return imgSaida;
    }



    // Método para converter RGB para YIQ
        private static double[] conversaoYIQ(int red, int green, int blue) {
            double[] yiq = new double[3];

            yiq[0] = 0.299 * red + 0.587 * green + 0.114 * blue;
            yiq[1] = 0.596 * red - 0.274 * green - 0.322 * blue;
            yiq[2] = 0.211 * red - 0.523 * green + 0.312 * blue;

            return yiq;
        }

        // Método para converter YIQ para RGB
        private static int[] conversaoRGB(double Y, double I, double Q) {
            int[] rgb = new int[3];

            rgb[0] = (int) (Y + 0.956 * I + 0.621 * Q);
            rgb[1] = (int) (Y - 0.272 * I - 0.647 * Q);
            rgb[2] = (int) (Y - 1.106 * I + 1.703 * Q);

            // Garantindo que os valores de RGB estejam dentro do intervalo 0-255
            for (int i = 0; i < 3; i++) {
                rgb[i] = Math.min(Math.max(rgb[i], 0), 255);
            }

            return rgb;
        }

        // Método para aplicar brilho aditivo na banda Y
        public static BufferedImage brilhoAditivoY(BufferedImage imgEntrada, double bilho) {
            int width = imgEntrada.getWidth();
            int height = imgEntrada.getHeight();
            BufferedImage imgSaida = new BufferedImage(
                    width,
                    height,
                    BufferedImage.TYPE_INT_RGB);

            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    Color cor = new Color(imgEntrada.getRGB(w, h));
                    double[] yiq = conversaoYIQ(cor.getRed(), cor.getGreen(), cor.getBlue());
                    double newY = Math.min(Math.max(yiq[0] + bilho, 0), 255);
                    int[] newRGB = conversaoRGB(newY, yiq[1], yiq[2]);
                    Color novaCor = new Color(newRGB[0], newRGB[1], newRGB[2]);
                    imgSaida.setRGB(w, h, novaCor.getRGB());
                }
            }
            return imgSaida;
        }

        // Método para aplicar brilho multiplicativo na banda Y
        public static BufferedImage brilhoMultiplicativoY(BufferedImage imgEntrada, double factor) {
            int width = imgEntrada.getWidth();
            int height = imgEntrada.getHeight();
            BufferedImage imgSaida = new BufferedImage(
                    width,
                    height,
                    BufferedImage.TYPE_INT_RGB);

            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    Color cor = new Color(imgEntrada.getRGB(w, h));
                    double[] yiq = conversaoYIQ(cor.getRed(), cor.getGreen(), cor.getBlue());
                    double newY = Math.min(Math.max(yiq[0] * factor, 0), 255);
                    int[] newRGB = conversaoRGB(newY, yiq[1], yiq[2]);
                    Color novaCor = new Color(newRGB[0], newRGB[1], newRGB[2]);
                    imgSaida.setRGB(w, h, novaCor.getRGB());
                }
            }
            return imgSaida;
        }

        // Método para aplicar negativo na banda Y
        // Método para aplicar negativo na banda Y
        public static BufferedImage negativoY(BufferedImage imgEntrada) {
            int width = imgEntrada.getWidth();
            int height = imgEntrada.getHeight();
            BufferedImage imgSaida = new BufferedImage(
                    width,
                    height,
                    BufferedImage.TYPE_INT_RGB);

            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    Color cor = new Color(imgEntrada.getRGB(w, h));
                    double[] yiq = conversaoYIQ(cor.getRed(), cor.getGreen(), cor.getBlue());
                    double newY = 255 - yiq[0];
                    int[] newRGB = conversaoRGB(newY, yiq[1], yiq[2]);
                    Color novaCor = new Color(newRGB[0], newRGB[1], newRGB[2]);
                    imgSaida.setRGB(w, h, novaCor.getRGB());
                }
            }
            return imgSaida;
        }
    }