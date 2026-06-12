# GitHub Actions Workflows for CorruptedCustomDrops

This project includes automated GitHub Actions workflows for building, testing, and releasing the plugin.

## Available Workflows

### 1. **Build Workflow** (`.github/workflows/build.yml`)

**Triggered on:**
- Push to `main` or `develop` branches
- Pull requests to `main` or `develop`
- Manual trigger via workflow_dispatch

**What it does:**
- Sets up Java 21 environment
- Runs Gradle build
- Uploads build artifacts for 30 days
- Creates release if tag is pushed
- Uploads JAR to GitHub Release

**Artifacts:**
- `CorruptedCustomDrops-build-[run-number].jar`

---

### 2. **Code Quality Workflow** (`.github/workflows/code-quality.yml`)

**Triggered on:**
- Push to `main` or `develop` branches
- Pull requests to `main` or `develop`

**What it does:**
- Runs code quality checks (spotbugs)
- Validates code formatting
- Generates build scan report
- Comments on PRs with results

**Reports:**
- Bug detection via SpotBugs
- Build scan analysis

---

### 3. **Release Workflow** (`.github/workflows/release.yml`)

**Triggered on:**
- Push of version tags (`v*.*.*`)
- Manual workflow dispatch

**What it does:**
- Builds the plugin
- Creates GitHub Release
- Uploads JAR as release asset
- Generates release notes

**Usage:**
```bash
# Tag a release
git tag v1.0.0
git push origin v1.0.0

# Workflow automatically:
# 1. Builds the plugin
# 2. Creates GitHub Release
# 3. Uploads JAR file
```

---

### 4. **Deployment Workflow** (`.github/workflows/deploy.yml`)

**Triggered on:**
- Release published
- Manual workflow dispatch

**What it does:**
- Verifies JAR compilation
- Extracts version information
- Creates deployment summary
- Stores artifacts for 90 days

**Summary includes:**
- Version number
- JAR filename
- Installation instructions

---

## How to Use

### Automatic Builds (Every Push)

```bash
# Push to main - automatically builds
git push origin main

# Check Actions tab for build status
# Download artifact from Actions > [Latest Workflow] > Artifacts
```

### Manual Build

```bash
# Go to Actions tab in GitHub
# Select "Build CorruptedCustomDrops"
# Click "Run workflow"
# Select branch and click "Run"
```

### Create a Release

```bash
# Create a version tag
git tag v1.0.1

# Push to GitHub
git push origin v1.0.1

# Workflow automatically:
# - Builds the plugin
# - Creates release on GitHub
# - Uploads JAR as asset
# - Generates release notes
```

---

## Accessing Build Artifacts

### Via GitHub UI

1. Go to repository
2. Click **Actions** tab
3. Select workflow run
4. Scroll to **Artifacts** section
5. Download `CorruptedCustomDrops-build-[number].jar`

### Via GitHub CLI

```bash
# Download latest artifact
gh run download -n CorruptedCustomDrops-build

# List all artifacts
gh run list --workflow build.yml
```

---

## Environment Variables

All workflows use:
- **Java Version:** 21
- **Distribution:** Temurin (Eclipse Adoptium)
- **Build Tool:** Gradle with caching

---

## Workflow Status Badges

Add these to your README.md:

```markdown
![Build](https://github.com/Amit4411/CorruptedCustomDrops/workflows/Build%20CorruptedCustomDrops/badge.svg)
![Quality](https://github.com/Amit4411/CorruptedCustomDrops/workflows/Code%20Quality%20%26%20Lint/badge.svg)
```

---

## Troubleshooting

### Build Fails

1. Check **Actions** tab for error logs
2. View the failing job's output
3. Common issues:
   - Java version mismatch
   - Gradle cache issues
   - Dependency resolution problems

**Fix:** Click workflow > Re-run jobs > Re-run all jobs

### Release Not Created

1. Ensure tag format is `v*.*.*` (e.g., `v1.0.0`)
2. Check **Release** workflow in Actions tab
3. Verify tag was pushed to GitHub

```bash
git tag v1.0.0
git push origin v1.0.0  # Must push tag!
```

### Artifact Not Found

1. Build must complete successfully
2. Check artifact retention days (30 for build, 90 for deploy)
3. Re-run workflow if artifact expired

---

## CI/CD Pipeline Overview

```
┌─────────────────────────────────────────────┐
│           Developer Pushes Code             │
└────────────────┬────────────────────────────┘
                 │
        ┌────────▼────────┐
        │  Build Workflow │
        └────────┬────────┘
                 │
        ┌────────▼────────────┐
        │ Code Quality Checks │
        └────────┬────────────┘
                 │
        ┌────────▼────────┐
        │  Upload Artifact│
        └────────┬────────┘
                 │
    ┌────────────┴────────────┐
    │                         │
    │ (If Tag Pushed)         │
    │                         │
┌───▼────────────────┐   ┌───▼──────────────┐
│ Release Workflow   │   │ Deploy Workflow  │
└────────────────────┘   └──────────────────┘
    │                         │
    └─────────┬───────────────┘
              │
    ┌─────────▼──────────┐
    │ GitHub Release     │
    │ JAR Available      │
    └────────────────────┘
```

---

## Next Steps

1. ✅ Push to `main` to trigger build
2. ✅ Check **Actions** tab to see workflow run
3. ✅ Download artifact from successful build
4. ✅ Tag a release when ready: `git tag v1.0.0 && git push origin v1.0.0`
5. ✅ JAR automatically uploaded to Releases

---

## Support

For workflow issues:
- Check GitHub Actions logs
- Review workflow YAML syntax
- Ensure repository has write permissions
- Verify Java/Gradle compatibility

All workflows are production-ready! 🚀
