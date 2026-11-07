param([Parameter(ValueFromRemainingArguments=$true)][string[]]$MavenArgs)
$ErrorActionPreference = "Stop"
$root = Split-Path -Parent $MyInvocation.MyCommand.Path
$version = "3.9.9"
$toolsDir = Join-Path $root ".tools"
$mavenHome = Join-Path $toolsDir "apache-maven-$version"
$mavenCmd = Join-Path $mavenHome "bin\mvn.cmd"
$existingMaven = Get-Command mvn -ErrorAction SilentlyContinue
if ($existingMaven) {
  & $existingMaven.Source @MavenArgs
  exit $LASTEXITCODE
}
if (-not (Test-Path $mavenCmd)) {
  New-Item -ItemType Directory -Path $toolsDir -Force | Out-Null
  $zip = Join-Path $toolsDir "apache-maven-$version-bin.zip"
  $url = "https://archive.apache.org/dist/maven/maven-3/$version/binaries/apache-maven-$version-bin.zip"
  Write-Host "Maven $version をダウンロードしています..."
  Invoke-WebRequest -Uri $url -OutFile $zip
  Expand-Archive -LiteralPath $zip -DestinationPath $toolsDir -Force
  Remove-Item -LiteralPath $zip -Force
}
& $mavenCmd @MavenArgs
exit $LASTEXITCODE
