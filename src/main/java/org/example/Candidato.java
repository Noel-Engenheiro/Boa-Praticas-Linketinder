package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

class Candidato {

        static Scanner teclado = new Scanner(System.in);
    public static Connection conectar() {
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "123");
            props.setProperty("ssl", "yes");
            String URL_SERVIDOR = "jdbc:postgresql://localhost:5432/postgres";

            try {
                return DriverManager.getConnection(URL_SERVIDOR, props);
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof ClassNotFoundException) {
                    System.err.println("Verifique o driver de conexão.");
                } else {
                    System.err.println("Verifique se o servidor está ativo.");
                }
                System.exit(-42);
                return null;
            }
        }
        static void desconectar(Connection conn) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        public static void listar_Candidato() {
            String listar_Candidato = "SELECT * FROM candidato";
            try {
                Connection conn = conectar();
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
                        System.out.println("Data de nascimento do candidato : " + resposta.getInt(4));
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
                Connection conn = conectar();
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
                desconectar(conn);
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
            Connection conn = conectar();
            PreparedStatement candidato = conn.prepareStatement(
                    BUSCAR_POR_CODIGO,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            candidato.setInt(1, codigo_candidato);
            ResultSet resultado = candidato.executeQuery();

            resultado.last();
            int qtd = resultado.getRow();
            resultado.beforeFirst();

            if (qtd > 0) {
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
                PreparedStatement upd = conn.prepareStatement(ATUALIZAR);

                upd.setInt(1,codigo_candidato);
                upd.setString(2,nome);
                upd.setString(3,sobrenome);
                upd.setString(4,data_de_nascimento);
                upd.setString(5,e_mail);
                upd.setInt(6,cpf);
                upd.setString(7,pais);
                upd.setInt(8,cep);
                upd.setString(9,descricao_pessoal);
                upd.setInt(10,senha);
                upd.close();
                desconectar(conn);
                System.out.println("O candidato" + codigo_candidato + " foi atualizado com sucesso");
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
                Connection conn = conectar();
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
                    desconectar(conn);
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

