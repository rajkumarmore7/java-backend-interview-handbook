# Java Backend Interview Handbook ☕

**Learn it → Code it → Explain it → Revise it.**

A public, practical interview-preparation handbook for experienced Java and Spring Boot developers. Written in straightforward English, with runnable Java 21 examples, diagrams, project-level scenarios and interview-ready answers.

> **Status:** Growing handbook. Two detailed study chapters and an initial 30-question answer bank are included. The remaining topics are organized in the roadmap—not presented as finished.

## 📚 Start reading

| Your goal | Open |
|---|---|
| Study Java execution | [JDK, JRE, JVM & execution flow](docs/notes/java-execution.md) |
| Understand collections internals | [How HashMap works internally](docs/notes/hashmap-internals.md) |
| Practice interviews | [30-question interview set](docs/interview-questions/30-questions.md) |
| Plan what to learn next | [Complete syllabus and progress tracker](docs/roadmap.md) |
| Run example code | [Java 21 mini-lab](examples/java21-mini-lab/README.md) |

## ✨ What makes this repository useful?

- **Study first:** explanation, mental model, annotated Java code, and practical application.
- **Then interview prep:** concise answers plus discussion prompts at senior level.
- **Visual learning:** Mermaid diagrams live next to the explanation.
- **Honest progress:** ✅ complete, 🟡 initial content, ⬜ planned.
- **Searchable website:** MkDocs Material + automatic GitHub Pages deployment.
- **No copied interview claims:** the curated 30-question list is inspired by a shared social-media image; it is **not verified** as an official interview set from any employer.

## 🧭 Learning workflow

```mermaid
flowchart LR
    A[Read concept] --> B[Trace diagram]
    B --> C[Run Java example]
    C --> D[Explain out loud]
    D --> E[Answer interview question]
    E --> F[Revise]
```

## 🛠 Run the site locally

Requires Python 3.10+:

```bash
python -m pip install -r requirements.txt
python -m mkdocs serve
```

Open the local URL printed by MkDocs. To run the Java examples, see the [mini-lab](examples/java21-mini-lab/README.md).

## 🌐 Publish the website on GitHub Pages

1. Create a **public** GitHub repository named `java-backend-interview-handbook` (or another name).
2. Push these files to the `main` branch.
3. In GitHub, open **Settings → Pages → Build and deployment** and set **Source: GitHub Actions**.
4. The included workflow publishes the documentation site after each push to `main`.
5. Your site typically appears at `https://YOUR-USERNAME.github.io/java-backend-interview-handbook/` if you use the suggested repository name. Replace `YOUR-USERNAME` with your actual username.

The workflow needs Pages permission provided by GitHub Actions. If your organization restricts Actions, review repository policies.

## 📁 Repository structure

```text
.
├── README.md
├── docs/
│   ├── index.md
│   ├── roadmap.md
│   ├── notes/
│   │   ├── java-execution.md
│   │   └── hashmap-internals.md
│   └── interview-questions/30-questions.md
├── examples/java21-mini-lab/
├── .github/workflows/deploy-docs.yml
├── mkdocs.yml
├── requirements.txt
├── CONTRIBUTING.md
└── LICENSE
```

## 🤝 Contributing

Corrections, clear examples, and diagrams are welcome. Please see [CONTRIBUTING.md](CONTRIBUTING.md). Don't include proprietary company code, secrets, or confidential interview materials.

## 📄 License

MIT. Educational content is provided as-is; validate framework/API behavior against the version you use.
