# PowerShell
$dir = "src/main/resources/assets/betterthanupdate/blockstates"
$templatePath = Join-Path $dir "oak_chair_block.json"
if (-not (Test-Path $templatePath)) { Write-Error "Template missing: $templatePath"; exit 1 }

$template = Get-Content -Raw $templatePath

$woods = @(
  @{ name="oak_";      texture="oak_chair_block" },
  @{ name="spruce_";   texture="spruce_chair_block" },
  @{ name="brich_";    texture="birch_chair_block" },
  @{ name="jungle_";   texture="jungle_chair_block" },
  @{ name="acacia_";   texture="acacia_chair_block" },
  @{ name="dark_oak_";  texture="dark_oak_chair_block" },
  @{ name="mangrove_"; texture="mangrove_chair_block" },
  @{ name="cherry_";   texture="cherry_chair_block" }
)

foreach ($w in $woods) {
  $outFile = Join-Path $dir ("{0}chair_block.json" -f $w.name)
  $outJson = $template -replace "oak_chair_block", $w.texture
  Set-Content -Path $outFile -Value $outJson -Encoding UTF8
  Write-Host "Generated $outFile"
}
