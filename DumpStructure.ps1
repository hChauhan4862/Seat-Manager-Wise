# Define the output file
$outputFile = "../output.txt"

# Remove the output file if it already exists
if (Test-Path $outputFile) {
    Remove-Item $outputFile
}

# Function to display structure and content
function Show-StructureAndContent {
    param(
        [string]$Path = ".",
        [string]$Indent = ""
    )

    Get-ChildItem -LiteralPath $Path | ForEach-Object {
        if ($_.PSIsContainer) {
            # Write directory name
            Add-Content -Path $outputFile -Value "$Indent+ $_"
            # Recursively process subdirectories
            Show-StructureAndContent -Path $_.FullName -Indent "$Indent  "
        } else {
            # Write file name
            Add-Content -Path $outputFile -Value "$Indent- $_"
            # Write file content
            Add-Content -Path $outputFile -Value "$Indent  Content:"
            Get-Content -LiteralPath $_.FullName | ForEach-Object { Add-Content -Path $outputFile -Value "$Indent    $_" }
        }
    }
}

# Start processing from the current directory
Show-StructureAndContent
