package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class TabelaDeVagas {
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
        static void listar_Vagas_Disponiveis() {
            @SuppressWarnings("SqlResolve") String listar_tabela_De_Vaga = "SELECT * FROM vaga";
            try {
                Connection conn = conectar();
                PreparedStatement vaga = conn.prepareStatement(
                        listar_tabela_De_Vaga,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );
                ResultSet resposta = vaga.executeQuery();
                resposta.last();
                int quantidade = resposta.getRow();
                resposta.beforeFirst();
                if (quantidade > 0) {
                    System.out.println("Listando vagas ");
                    while (resposta.next()) {
                        System.out.println(" Código das vagas : " + resposta.getInt(1));
                        System.out.println(" Nome das vagas : " + resposta.getString(2));
                        System.out.println(" Descrição das vagas : " + resposta.getString(3));
                        System.out.println(" Competências das vagas : " + resposta.getString(4));
                        System.out.println(" Local das vagas : " + resposta.getString(5));
                        System.out.println("------------------------");
                    }
                } else {
                    System.out.println("Não existem vagas cadastradas.");
                    System.out.println("------------------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao buscar vagas.");
                System.exit(-42);
            }
        }
        static void inserir_Vagas_Na_Tabela(){
            System.out.println("Informe o código da vaga : ");
            int codigo_vaga = teclado.nextInt();
            System.out.println("Informe o nome da vaga  : ");
            teclado.nextLine();
            String nome_vaga = teclado.nextLine();
            System.out.println("Informe a descrição da vaga : ");
            String descricao_vaga = teclado.nextLine();
            System.out.println("Informe as competências exigidas : ");
            String competencia_vaga = teclado.nextLine();
            System.out.println("Informe o local da vaga : ");
            String local_vaga = teclado.nextLine();

            String INSERIR = "INSERT INTO vaga (codigo_vaga, nome_Vaga, descricao_Vaga, competencia_Vaga, local_Vaga) VALUES (?, ?, ?, ?, ?)";
            try{
                Connection conn = conectar();
                PreparedStatement salvar = conn.prepareStatement(INSERIR);

                salvar.setInt(1, codigo_vaga);
                salvar.setString(2, nome_vaga);
                salvar.setString(3, descricao_vaga);
                salvar.setString(4, competencia_vaga);
                salvar.setString(5, local_vaga);

                salvar.executeUpdate();
                salvar.close();
                desconectar(conn);
                System.out.println("A vaga " + nome_vaga + " foi inserida com sucesso.");
            } catch(Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao inserir o nome.");
                System.exit(-42);
            }
        }
    public static void atualizar_Vagas_Da_Tabela() {
        System.out.println("Informe o código do candidato");
        int codigo_vaga = teclado.nextInt();
        String BUSCAR_POR_CODIGO = "SELECT * FROM vaga WHERE codigo_vaga=?";

        try {
            Connection conn = conectar();
            PreparedStatement vaga = conn.prepareStatement(
                    BUSCAR_POR_CODIGO,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            vaga.setInt(1, codigo_vaga);
            ResultSet res = vaga.executeQuery();

            res.last();
            int quantidade = res.getRow();
            res.beforeFirst();

            if (quantidade > 0) {

                System.out.println("Informe o nome da vaga  : ");
                teclado.nextLine();
                String nome_vaga = teclado.nextLine();
                System.out.println("Informe a descrição da vaga : ");
                String descricao_vaga = teclado.nextLine();
                System.out.println("Informe as competências exigidas : ");
                String competencia_vaga = teclado.nextLine();
                System.out.println("Informe o local da vaga : ");
                String local_vaga = teclado.nextLine();

                String ATUALIZAR = "UPDATE vaga SET nome_vaga =? , descricao_vaga=? , competencia_vaga=? , local_vaga=? WHERE  codigo_vaga=?";
                PreparedStatement upd = conn.prepareStatement(ATUALIZAR);

                upd.setString(2,nome_vaga);
                upd.setString(3,descricao_vaga);
                upd.setString(4,competencia_vaga);
                upd.setString(5,local_vaga);
                upd.close();
                desconectar(conn);
                System.out.println("A vaga " + codigo_vaga + " foi atualizado com sucesso");
            }else {
                System.out.println("Não existe a vaga " + codigo_vaga + " informada");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não foi possível as vagas");
            System.exit(-42);
        }
    }
        public static void deletar_Vagas_Da_Tabela(){
            @SuppressWarnings("SqlResolve") String DELETAR = "DELETE FROM Vaga cod_Vaga WHERE cod_Vaga= ?";
            String BUSCAR_POR_CODIGO = "SELECT* FROM competencia WHERE codigo_competencia_candidato=?";

            System.out.println("Nome da vaga : ");
            int codigo_vaga = teclado.nextInt();

            try {
                Connection conn = conectar();
                PreparedStatement vaga = conn.prepareStatement(
                        BUSCAR_POR_CODIGO,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );
                vaga.setInt(1,codigo_vaga);
                ResultSet resultado = vaga.executeQuery();

                resultado.last();
                int quantidade = resultado.getRow();
                resultado.beforeFirst();

                if (quantidade > 0){
                    PreparedStatement deletar = conn.prepareStatement(DELETAR);
                    deletar.setInt(1, codigo_vaga);
                    deletar.executeUpdate();
                    deletar.close();
                    desconectar(conn);
                    System.out.println("Vaga deletada com sucesso");

                }else{
                    System.out.println("Não existe a vaga informada");
                }
            }
            catch(Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao deletar vaga");
            }
        }
    }
