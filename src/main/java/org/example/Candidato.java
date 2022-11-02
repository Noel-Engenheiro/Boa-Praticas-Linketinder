package org.example;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

class Candidato {

        static Scanner teclado = new Scanner(System.in);

        static Coneccao conectar = new Coneccao();
        static Coneccao desconectar = new Coneccao();

        public static void listar_Candidato() {
            String listar_Candidato = "SELECT * FROM candidato";
            try {
                Connection conn = conectar.conectar();
                PreparedStatement candidato = conn.prepareStatement(
                        listar_Candidato,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );
                ResultSet resposta = candidato.executeQuery();
                resposta.last();
                int quantidade = resposta.getRow();
                resposta.beforeFirst();
                if (quantidade > 0) {
                    System.out.println("Listando Candidatos ");
                    while (resposta.next()) {
                        System.out.println("Código do candidato : " + resposta.getInt(1));
                        System.out.println("Nome do candidato : " + resposta.getString(2));
                        System.out.println("Sobrenome do candidato : " + resposta.getString(3));
                        System.out.println("Data de nascimento do candidato : " + resposta.getString(4));
                        System.out.println("E-mail do candidato : " + resposta.getString(5));
                        System.out.println("CPF do candidato : " + resposta.getInt(6));
                        System.out.println("País do candidato : " + resposta.getString(7));
                        System.out.println("CEP do candidato : " + resposta.getInt(8));
                        System.out.println("Descrição pessoal do candidato : " + resposta.getString(9));
                        System.out.println("Senha do candidato : " + resposta.getInt(10));
                        System.out.println("------------------------");
                    }
                } else {
                    System.out.println("Não existem usuários cadastrados.");
                    System.out.println("------------------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao buscar candidatos.");
                System.exit(-42);
            }
        }
        public static void inserir_Candidato() {
            System.out.println("Informe o código do Candidato : ");
            int codigo_candidato = teclado.nextInt();
            System.out.println("Informe o nome do Candidato : ");
            teclado.nextLine();
            String nome = teclado.nextLine();
            System.out.println("Informe sobrenome do Candidato : ");
            String sobrenome = teclado.nextLine();
            System.out.println("Informe a data de nascimento do Candidato : ");
            String data_de_nascimento = teclado.nextLine();
            System.out.println("Informe o e-mail do Candidato : ");
            String e_mail = teclado.nextLine();
            System.out.println("Informe o CPF do Candidato : ");
            int cpf = teclado.nextInt();
            System.out.println("Informe o país do Candidato : ");
            teclado.nextLine();
            String pais = teclado.nextLine();
            System.out.println("Informe o CEP do Candidato : ");
            int cep = teclado.nextInt();
            System.out.println("Informe a descrição pessoal do Candidato : ");
            teclado.nextLine();
            String descricao_pessoal = teclado.nextLine();
            System.out.println("Informe a senha do Candidato  : ");
            int senha = teclado.nextInt();

            String INSERIR = "INSERT INTO candidato (codigo_candidato, nome, sobrenome, data_de_nascimento, e_mail, cpf, pais, cep, descricao_pessoal, senha) VALUES (?,?,?,?,?,?,?,?,?,?)";
            try{
                Connection conn = conectar.conectar();
                PreparedStatement salvar = conn.prepareStatement(INSERIR);

                salvar.setInt(1, codigo_candidato);
                salvar.setString(2, nome);
                salvar.setString(3, sobrenome);
                salvar.setString(4, data_de_nascimento);
                salvar.setString(5, e_mail);
                salvar.setInt(6, cpf);
                salvar.setString(7, pais);
                salvar.setInt(8, cep);
                salvar.setString(9, descricao_pessoal);
                salvar.setInt(10, senha);

                salvar.executeUpdate();
                salvar.close();
                desconectar.desconectar(conn);
                System.out.println("O candidato " + nome + sobrenome + " foi inserido com sucesso.");
            } catch(Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao inserir a candidato.");
                System.exit(-42);
            }
        }

        public static void atualizar_Dados_Do_Candidato() {
        System.out.println("Informe o código do candidato");
        int codigo_candidato = teclado.nextInt();
        String BUSCAR_POR_CODIGO = "SELECT * FROM candidato WHERE codigo_candidato =?";

        try {
            Connection conn = conectar.conectar();
            PreparedStatement candidato = conn.prepareStatement(
                    BUSCAR_POR_CODIGO,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            candidato.setInt(1, codigo_candidato);
            ResultSet resultado = candidato.executeQuery();

            resultado.last();
            int quantidade = resultado.getRow();
            resultado.beforeFirst();

            if (quantidade > 0) {
                System.out.println("Informe o nome do Candidato : ");
                teclado.nextLine();
                String nome = teclado.nextLine();
                System.out.println("Informe sobrenome do Candidato : ");
                String sobrenome = teclado.nextLine();
                System.out.println("Informe a data de nascimento do Candidato : ");
                String data_de_nascimento = teclado.nextLine();
                System.out.println("Informe o e-mail do Candidato : ");
                String e_mail = teclado.nextLine();
                System.out.println("Informe o CPF do Candidato : ");
                int cpf = teclado.nextInt();
                System.out.println("Informe o país do Candidato : ");
                teclado.nextLine();
                String pais = teclado.nextLine();
                System.out.println("Informe o CEP do Candidato : ");
                int cep = teclado.nextInt();
                System.out.println("Informe a descrição pessoal do Candidato : ");
                teclado.nextLine();
                String descricao_pessoal = teclado.nextLine();
                System.out.println("Informe a senha do Candidato  : ");
                int senha = teclado.nextInt();

                String ATUALIZAR = "UPDATE candidato SET nome=? , sobrenome=? , data_de_nascimento=? , e_mail=? , cpf=? , pais=? , cep=? , descricao_pessoal=? , senha=? WHERE  codigo_candidato=?";
                PreparedStatement update = conn.prepareStatement(ATUALIZAR);

                update.setString(1,nome);
                update.setString(2,sobrenome);
                update.setString(3,data_de_nascimento);
                update.setString(4,e_mail);
                update.setInt(5,cpf);
                update.setString(6,pais);
                update.setInt(7,cep);
                update.setString(8,descricao_pessoal);
                update.setInt(9,senha);
                update.setInt(10, codigo_candidato);

                update.executeUpdate();
                update.close();
                desconectar.desconectar(conn);
                System.out.println("O candidato " + codigo_candidato + " foi atualizado com sucesso");
            }else {
                System.out.println("Não existe o candidato " + codigo_candidato + " informado");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não foi possível atualizar os candidatos");
            System.exit(-42);
        }
    }
        public static void deletar_Candidato(){
            String DELETAR = "DELETE FROM candidato cod_candidato WHERE codigo_candidato= ?";
            String BUSCAR_POR_CODIGO = "SELECT* FROM candidato WHERE codigo_candidato=?";

            System.out.println("Código do candidato : ");
            int codigo_candidato = teclado.nextInt();

            try {
                Connection conn = conectar.conectar();
                PreparedStatement candidato = conn.prepareStatement(
                        BUSCAR_POR_CODIGO,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );
                candidato.setInt(1,codigo_candidato);
                ResultSet resultado = candidato.executeQuery();

                resultado.last();
                int quantidade = resultado.getRow();
                resultado.beforeFirst();

                if (quantidade > 0){
                    PreparedStatement deletar = conn.prepareStatement(DELETAR);
                    deletar.setInt(1, codigo_candidato);
                    deletar.executeUpdate();
                    deletar.close();
                    desconectar.desconectar(conn);
                    System.out.println("Candidato deletado com sucesso");

                }else{
                    System.out.println("Não existe o candidato informado");
                }
            }
            catch(Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao deletar candidato");
            }
        }
    }

