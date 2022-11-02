package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Empresa {
        static Scanner teclado = new Scanner(System.in);

        static Coneccao conectar = new Coneccao();
        static Coneccao desconectar = new Coneccao();
        public static void listar_Empresa() {
            String listar_Empresa = "SELECT * FROM empresa";
            try {
                Connection conn = conectar.conectar();
                PreparedStatement empresa = conn.prepareStatement(
                        listar_Empresa,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );
                ResultSet resposta = empresa.executeQuery();
                resposta.last();
                int quantidade = resposta.getRow();
                resposta.beforeFirst();
                if (quantidade > 0) {
                    System.out.println("Listando Empresas ");
                    while (resposta.next()) {
                        System.out.println(" Código da empresa : " + resposta.getInt(1));
                        System.out.println(" Nome da empresa : " + resposta.getString(2));
                        System.out.println(" E-mail da empresa : " + resposta.getString(3));
                        System.out.println(" Descrição da empresa : " + resposta.getString(4));
                        System.out.println(" País da empresa : " + resposta.getString(5));
                        System.out.println(" CEP da empresa : " + resposta.getInt(6));
                        System.out.println(" Senha da empresa : " + resposta.getInt(7));
                        System.out.println(" CNPJ da empresa : " + resposta.getInt(8));
                        System.out.println("------------------------");
                    }
                } else {
                    System.out.println("Não existem empresas cadastradas.");
                    System.out.println("------------------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao buscar empresas.");
                System.exit(-42);
            }
        }
        public static void inserir_Empresa (){
            System.out.println("Informe o código da empresa : ");
            int codigo_empresa = teclado.nextInt();
            System.out.println("Informe o nome da empresa : ");
            teclado.nextLine();
            String nome = teclado.nextLine();
            System.out.println("Informe o e-mail da empresa : ");
            String e_mail = teclado.nextLine();
            System.out.println("Desrição pessoal esperada pela empresa : ");
            String descricao_pessoal = teclado.nextLine();
            System.out.println("Informe o país da empresa : ");
            String pais_empresa = teclado.nextLine();
            System.out.println("Informe o cep da empresa : ");
            int cep = teclado.nextInt();
            System.out.println("Informe a senha : ");
            int senha = teclado.nextInt();
            System.out.println("Informe o cnpj da empresa : ");
            int cnpj = teclado.nextInt();

            String INSERIR = "INSERT INTO empresa (codigo_empresa, nome, e_mail, descricao_pessoal, pais_empresa, cep, senha, cnpj) VALUES (?, ?, ?, ?,?,?,?,?)";
            try{
                Connection conn = conectar.conectar();
                PreparedStatement salvar = conn.prepareStatement(INSERIR);

                salvar.setInt(1, codigo_empresa);
                salvar.setString(2, nome);
                salvar.setString(3, e_mail);
                salvar.setString(4, descricao_pessoal);
                salvar.setString(5, pais_empresa);
                salvar.setInt(6,cep);
                salvar.setInt(7, senha);
                salvar.setInt(8, cnpj);

                salvar.executeUpdate();
                salvar.close();
                desconectar.desconectar(conn);
                System.out.println("A empresa " + nome  + " foi inserida com sucesso.");
            } catch(Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao inserir a empresa.");
                System.exit(-42);
            }
        }
        public static void atualizar_Dados_Da_Empresa() {
        System.out.println(" Informe o código da empresa ");
        int codigo_empresa = teclado.nextInt();
        String BUSCAR_POR_CODIGO = "SELECT * FROM empresa WHERE codigo_empresa =?";

        try {
            Connection conn = conectar.conectar();
            PreparedStatement empresa = conn.prepareStatement(
                    BUSCAR_POR_CODIGO,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            empresa.setInt(1, codigo_empresa);
            ResultSet resultado = empresa.executeQuery();

            resultado.last();
            int quantidade = resultado.getRow();
            resultado.beforeFirst();

            if (quantidade > 0) {
                System.out.println("Informe o nome da empresa : ");
                teclado.nextLine();
                String nome = teclado.nextLine();
                System.out.println("Informe o e-mail da empresa : ");
                String e_mail = teclado.nextLine();
                System.out.println("Desrição pessoal esperada pela empresa : ");
                String descricao_pessoal = teclado.nextLine();
                System.out.println("Informe o país da empresa : ");
                String pais_empresa = teclado.nextLine();
                System.out.println("Informe o cep da empresa : ");
                int cep = teclado.nextInt();
                System.out.println("Informe a senha : ");
                int senha = teclado.nextInt();
                System.out.println("Informe o cnpj da empresa : ");
                int cnpj = teclado.nextInt();

                String ATUALIZAR = "UPDATE empresa SET nome=? , e_mail=? , descricao_pessoal=? , pais_empresa=? , cep=? , senha=? , cnpj=? WHERE codigo_empresa=?";
                PreparedStatement update = conn.prepareStatement(ATUALIZAR);

                update.setString(1,nome);
                update.setString(2,e_mail);
                update.setString(3,descricao_pessoal);
                update.setString(4,pais_empresa);
                update.setInt(5,cep);
                update.setInt(6,senha);
                update.setInt(7,cnpj);
                update.setInt(8,codigo_empresa);

                update.executeUpdate();
                update.close();
                desconectar.desconectar(conn);
                System.out.println("A empresa " + nome + " foi atualizada com sucesso");

            }else {
                System.out.println("Não existe a empresa " + codigo_empresa + " informada");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Não foi possível atualizar a empresa");
            System.exit(-42);
        }
    }
        public static void deletar_Empresa(){
            String DELETAR = "DELETE FROM empresa codigo_empresa WHERE codigo_empresa= ?";
            String BUSCAR_POR_CODIGO = "SELECT* FROM empresa WHERE codigo_empresa=?";

            System.out.println("Código da empresa : ");
            int codigo_empresa = teclado.nextInt();

            try {
                Connection conn = conectar.conectar();
                PreparedStatement empresa = conn.prepareStatement(
                        BUSCAR_POR_CODIGO,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                );
                empresa.setInt(1,codigo_empresa);
                ResultSet resultado = empresa.executeQuery();

                resultado.last();
                int quantidade = resultado.getRow();
                resultado.beforeFirst();

                if (quantidade > 0){
                    PreparedStatement deletar = conn.prepareStatement(DELETAR);
                    deletar.setInt(1, codigo_empresa);
                    deletar.executeUpdate();
                    deletar.close();
                    desconectar.desconectar(conn);
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


