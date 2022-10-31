package org.example;

import java.util.Scanner;

public class Aplicativo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
                boolean exit = false;
                while(!exit){
                    System.out.println("Escolha uma opção : " + "\n 1 Listar Candidato " + "\n 2 Inserir Candidato " + "\n 3 Atualizar dados do Candidato " +
                            "\n 4 Deletar Candidato " + "\n 5 Listar Empresa " + "\n 6 Inserir Empresa " + "\n 7 Atualizar dados da Empresa " +
                            "\n 8 Deletar Empresa " + "\n 9 Listar Competência do Candidato " + "\n 10 Inserir Competência do Candidato " + "\n 11 Atualizar Competência do Candidato "+
                            "\n 12 Deletar Competência do candidato" + "\n 13 Listar Vagas disponíveis" + "\n 14 Inserir Vagas na Tabela" +
                            "\n 15 Atualizar Vagas da tabela " + "\n 16 Deletar Vagas da tabela " + "\n 17 Sair do programa ");
                    int opcao = input.nextInt();
                    switch (opcao){
                        case 1 :
                            Candidato.listar_Candidato();
                            break;
                        case 2 :
                            Candidato.inserir_Candidato();
                            break;
                        case 3 :
                            Candidato.atualizar_Dados_Do_Candidato();
                            break;
                        case 4 :
                            Candidato.deletar_Candidato();
                            break;
                        case 5 :
                            Empresa.listar_Empresa();
                            break;
                        case 6 :
                            Empresa.inserir_Empresa();
                            break;
                        case 7 :
                            Empresa.atualizar_Dados_Da_Empresa();
                        case 8 :
                            Empresa.deletar_Empresa();
                            break;
                        case 9 :
                            Competencia.listar_Competencia_Candidato();
                            break;
                        case 10 :
                            Competencia.inserir_Competencia_Candidato();
                            break;
                        case 11 :
                            Competencia.atualizar_Competencia_Candidato();
                        case 12 :
                            Competencia.deletar_Competencia_Candidato();
                            break;
                        case 13 :
                            TabelaDeVagas.listar_Vagas_Disponiveis();
                            break;
                        case 14 :
                            TabelaDeVagas.inserir_Vagas_Na_Tabela();
                            break;
                        case 15 :
                            TabelaDeVagas.atualizar_Vagas_Da_Tabela();
                        case 16 :
                            TabelaDeVagas.deletar_Vagas_Da_Tabela();
                            break;
                        case 17 :
                            exit = true;
                            break;
                        default :
                            System.out.println("Escolha uma opção válida");
                    }
                }
            }
        }
