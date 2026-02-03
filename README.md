

# ☂️ Umbrella E-commerce API

Este projeto consiste no desenvolvimento de uma **API RESTful** com Java e Spring Boot, criada com o objetivo principal de simular um ambiente de trabalho real e aplicar **fluxos de versionamento profissional com Git**.

Embora a regra de negócio seja um sistema de E-commerce simplificado (Usuários e Pedidos), o foco do desenvolvimento foi a aplicação rigorosa de boas práticas de **Git Flow**, simulação de trabalho em equipe e resolução de conflitos.

## 🎯 Objetivos do Projeto

Além da construção da API, este repositório serve como prova de conceito para:

* **Git Flow & Branching Strategy:** Uso de branches específicas (`feature/*`, `hotfix/*`, `develop`, `main`) para organizar o ciclo de vida do software.
* **Simulação de Teamwork:** Gerenciamento de múltiplos desenvolvedores virtuais (Dev A no Linux, Dev B no Windows) para provocar e resolver **Conflitos de Merge**.
* **Gestão de Crise (Hotfix):** Simulação de erros críticos em produção (StackOverflow/LazyInitialization), criação de correções emergenciais e sincronização entre branches de produção e desenvolvimento.
* **Release Management:** Uso de **Tags** (v1.0.0, v1.0.1) para versionamento semântico de entregas.
* **Code Review & Pull Requests:** Integração de código via interface do GitHub, garantindo rastreabilidade e segurança (Commits assinados/verificados).

## 🛠️ Tecnologias Utilizadas

* **Java 17** & **Spring Boot 3**
* **H2 Database** (Banco em memória para prototipagem rápida)
* **JPA / Hibernate** (Persistência de dados)
* **Git & GitHub** (Versionamento avançado)

---


# 📘 Guia de Workflow & Comandos do Projeto

Este documento serve como um guia rápido para o fluxo de trabalho Git utilizado neste projeto (Git Flow Simplificado), separando responsabilidades por papéis e situações.

---

## 👨‍💻 Perfil: Desenvolvedor (Dev)

**Foco:** Criar funcionalidades (`features`), corrigir bugs locais e manter o código atualizado.

### 1. 🌅 Iniciando o dia (Sincronização)

Antes de começar qualquer código, garanta que sua base está atualizada com o trabalho do time.

```bash
# Vá para a branch de desenvolvimento
git checkout develop

# Baixe as atualizações da nuvem
git pull origin develop

```

### 2. 🔨 Iniciando uma Nova Tarefa

Nunca code na `develop`. Crie uma branch específica para sua funcionalidade.

```bash
# Padrão de nome: feature/nome-da-tarefa
git checkout -b feature/minha-nova-funcionalidade

```

### 3. 💾 Salvando o Progresso

Ao terminar uma etapa ou o dia de trabalho.

```bash
# Adiciona todos os arquivos modificados
git add .

# Cria o pacote com uma mensagem clara (Inglês ou Português padrão)
# Ex: "feat: create UserService" ou "fix: resolve loop in JSON"
git commit -m "tipo: descrição do que foi feito"

# Envia para o GitHub (cria a branch lá se não existir)
git push origin feature/minha-nova-funcionalidade

```

### 4. 🧹 Faxina (Pós-Merge)

Depois que seu código foi aprovado e integrado (mergeado) no GitHub, apague o rascunho do seu computador.

```bash
# Volte para a base
git checkout develop

# Atualize (para baixar o seu próprio código que foi mergeado)
git pull origin develop

# Delete a branch antiga
git branch -d feature/minha-nova-funcionalidade

```

---

## 🕵️‍♂️ Perfil: Tech Lead / Maintainer

**Foco:** Aprovar Pull Requests, realizar Merges de Release, criar Tags de versão e gerenciar crises (Hotfixes).

### 1. 🚀 Release: Subir para Produção

Quando a `develop` está estável e pronta para virar uma versão oficial na `main`.

```bash
# Garanta que a develop está atualizada
git checkout develop
git pull origin develop

# Vá para a main
git checkout main
git pull origin main

# Faça a fusão
git merge develop

# Envie para a produção
git push origin main

```

### 2. 🏷️ Versionamento (Tags)

Após atualizar a `main`, crie uma "foto" daquela versão.

```bash
# Criar a etiqueta (Ex: v1.0.0, v1.1.0)
git tag -a v1.0.0 -m "Release da Versão 1.0.0 - Funcionalidades X e Y"

# Enviar a etiqueta para o GitHub
git push origin v1.0.0

```

### 3. 🚑 Hotfix (Crise em Produção)

Quando um bug crítico aparece na `main` e não dá para esperar o ciclo normal.

**Passo A: Criar a correção a partir da Main**

```bash
git checkout main
git checkout -b hotfix/nome-do-erro-critico
# ... (Dev faz a correção, commit e push) ...

```

**Passo B: Aplicar a correção na Main (Para o cliente)**

```bash
git checkout main
git merge hotfix/nome-do-erro-critico
git push origin main
git tag -a v1.0.1 -m "Hotfix: correção crítica"
git push origin v1.0.1

```

**Passo C: Replicar a correção na Develop (Para o time)**
*Essencial para o bug não voltar na próxima versão.*

```bash
git checkout develop
git merge hotfix/nome-do-erro-critico
git push origin develop

```

**Passo D: Deletar a branch de emergência**

```bash
git branch -d hotfix/nome-do-erro-critico

```

---

## 🆘 Glossário de Problemas Comuns

| Situação | Comando/Solução |
| --- | --- |
| **Conflito de Merge** | Ocorre quando dois devs mexem na mesma linha. Resolva manualmente editando o arquivo, removendo `<<<<` e `>>>>`, depois dê `git add .` e `git commit`. |
| **"Branch not found"** | Você tentou apagar uma branch que não existe ou digitou errado. Use `git branch` para listar todas. |
| **Erro ao fazer Push** | Geralmente acontece quando o GitHub tem algo que você não tem. Dê um `git pull` antes de tentar o `push` novamente. |
| **StackOverflowError** | Loop infinito no JSON (Entidade chama Entidade). Solução: `@JsonIgnore` em um dos lados da relação. |
| **LazyInitialization** | Erro ao carregar lista do banco. Solução: Adicionar `fetch = FetchType.EAGER` no `@OneToMany`. |

---

> **Nota:** Este fluxo segue os princípios do Git Flow adaptado para agilidade. Mantenha a branch `main` sempre estável (Deployable).
