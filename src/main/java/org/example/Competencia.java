package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
public class Competencia {
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
        public static void listar_Competencia_Candidato() {
            String listar_Competencia = "SELECT * FROM  competencia";
            try {
                Connection conn = conectar();
                PreparedStatement competencia = conn.prepareStatement(
                        listar_Competencia,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );
                ResultSet resposta = competencia.executeQuery();
                resposta.last();
                int quantidade = resposta.getRow();
                resposta.beforeFirst();
                if (quantidade > 0) {
                        System.out.println("Listando Competência ");
                    while (resposta.next()) {
                        System.out.println(" Competência do candidato : " + resposta.getInt(1));
                        System.out.println("Descrição : " + resposta.getString(2));
                        System.out.println("------------------------");
                    }
                } else {
                    System.out.println("Não existem competências cadastradas.");
                    System.out.println("------------------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao buscar competências.");
                System.exit(-42);
            }
        }
        public static void inserir_Competencia_Candidato (){
            System.out.println("Informe o código da competência do Candidato : ");
            int cod_competencia_candidato = teclado.nextInt();
            System.out.println("Informe a descrição do Candidato  : ");
            teclado.nextLine();
            String descricao = teclado.nextLine();

            String INSERIR = "INSERT INTO competencia (codigo_competencia_candidato, descricao) VALUES (?, ?)";
            try{
                Connection conn = conectar();
                PreparedStatement salvar = conn.prepareStatement(INSERIR);

                salvar.setInt(1, cod_competencia_candidato);
                salvar.setString(2, descricao);

                salvar.executeUpdate();
                salvar.close();
                desconectar(conn);
                System.out.println("A competência " + descricao + " foi inserida com sucesso.");
            } catch(Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao inserir a competência.");
                System.exit(-42);
            }
        }
    public static void atualizar_Competencia_Candidato() {
        System.out.println("Informe a competência");
        int codigo_competencia_candidato = teclado.nextInt();
        String BUSCAR_POR_CODIGO = "SELECT * FROM competencia WHERE codigo_competencia_candidato =?";

        try {
            Connection conn = conectar();
            PreparedStatement competencia = conn.prepareStatement(
                    BUSCAR_POR_CODIGO,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            competencia.setInt(1, codigo_competencia_candidato);
            ResultSet res = competencia.executeQuery();

            res.last();
            int qtd = res.getRow();
            res.beforeFirst();

            if (qtd > 0) {
                System.out.println("Informe a descrição do Candidato  : ");
                teclado.nextLine();
                String descricao = teclado.nextLine();

                String ATUALIZAR = "UPDATE competencia SET codigo_competencia_candidato=? , descricao=? WHERE  codigo_competencia_candidato=?";;
                PreparedStatement upd = conn.prepareStatement(ATUALIZAR);

                upd.setString(2,descricao);
                upd.close();
                desconectar(conn);
                System.out.println("A competência " + codigo_competencia_candidato + " foi atualizada com sucesso");
            }else {
                System.out.println("Não existe a vaga " + codigo_competencia_candidato + " informada");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não foi possível as vagas");
            System.exit(-42);
        }
    }
        public static void deletar_Competencia_Candidato(){
            String DELETAR = "DELETE FROM competencia codigo_competencia_candidato WHERE codigo_competencia_candidato= ?";
            String BUSCAR_POR_CODIGO = "SELECT* FROM competencia WHERE codigo_competencia_candidato=?";

            System.out.println("Nome da competência : ");
            int codigo_competencia_candidato = teclado.nextInt();

            try {
                Connection conn = conectar();
                PreparedStatement competencia = conn.prepareStatement(
                        BUSCAR_POR_CODIGO,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );
                competencia.setInt(1,codigo_competencia_candidato);
                ResultSet resultado = competencia.executeQuery();

                resultado.last();
                int quantidade = resultado.getRow();
                resultado.beforeFirst();

                if (quantidade > 0){
                    PreparedStatement deletar = conn.prepareStatement(DELETAR);
                    deletar.setInt(1, codigo_competencia_candidato);
                    deletar.executeUpdate();
                    deletar.close();
                    desconectar(conn);
                    System.out.println("Competência deletada com sucesso");

                }else{
                    System.out.println("Não existe a competência informada");
                }
            }
            catch(Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao deletar competência");
            }
        }
    }

