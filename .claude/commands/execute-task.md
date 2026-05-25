You are a task branch agent. When invoked, parse the argument `$ARGUMENTS` as follows:

- **TASK_NAME**: the first word (e.g. `TASK1`)
- **DESCRIPTION**: everything after the first word (e.g. `Add a toast saying hello at startup of app`)

Example invocation: `execute-task TASK1 Add a toast saying hello at startup of app`

**Repository:** mutiss/RickMortyKMPClaude  
**Base branch:** develop  
**Branch naming convention:** `feature/task-TASK_NAME`

Follow these steps exactly:

1. Use `mcp__github__list_branches` to confirm that the `develop` branch exists in `mutiss/RickMortyKMPClaude`.
2. Use `mcp__github__get_commit` (or `mcp__github__get_file_contents` on the branch) to get the latest SHA of `develop`.
3. Use `mcp__github__create_branch` to create a new branch named `feature/task-TASK_NAME` from the SHA of `develop`.
4. Report back with:
   - The branch name created
   - The base commit SHA it was created from
   - A confirmation that `develop` was not modified

5. Run `git fetch origin feature/task-TASK_NAME` to pull the new branch locally.
6. Run `git checkout feature/task-TASK_NAME` to switch to the new branch.
7. Confirm the active branch with `git branch --show-current` and include the result in the report.

8. Generate a step-by-step implementation plan for the task using both TASK_NAME and DESCRIPTION as context:
   - Start with a one-line summary: **Task TASK_NAME** — DESCRIPTION
   - List each implementation step in numbered order
   - For each step include: what to implement, which files/modules are likely affected, and any dependencies on prior steps
   - Keep steps atomic and actionable (one clear deliverable per step)
   - End with a "Definition of Done" checklist the developer can verify against

Do NOT push any commits, do NOT modify `develop`, and do NOT create a pull request unless explicitly asked.

---

## Automatic continuation

Once the plan is generated, **immediately** invoke the `execute-task-approve` skill with the same `$ARGUMENTS` — do not wait for the user to type it. The pipeline continues automatically.
