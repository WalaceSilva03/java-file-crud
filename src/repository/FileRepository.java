package repository;

import java.io.*;


public class FileRepository {
    public void createForm(){
        File file = new File("src/data/formulario.txt");
        try(FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw);) {
            bw.write("1 - Qual o nome e sobrenome do pet?\n2 - Qual o tipo do pet (Cachorro/Gato)\n" +
                    "3 - Qual o sexo do animal?\n4 - Qual endereço e bairro que ele foi encontrado?\n5 - Qual a idade aproximada do pet?\n" +
                    "6 - Qual o peso aproximado do pet?\n7 - Qual a raça do pet?");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readForm(File file){
        try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr);){
            String linha;
            while((linha=br.readLine()) != null){
                System.out.println(linha);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readLineForm(int lineReader, File file){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            int count = 0;
            while((line = br.readLine()) != null){
                count++;
                if(count == lineReader) {
                    System.out.println(line);
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
