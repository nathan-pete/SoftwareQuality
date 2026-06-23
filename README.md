# SoftwareQuality
This GitHub repository is the project submission for the Software Quality Course for Academic Year 2025-2026

## Student Information
Name: Nathan P. Pais D'costa
<br>
Student Number: 5045800

Name: Stefani Margaritova
<br>
Student Number: 4967399

## About this branch

This branch (`JabberpointV1`) contains the original, unmodified version of JabberPoint as provided for this assignment. 
It represents the baseline codebase before any refactoring work begins, and exists so the as-built design 
(and its SOLID violations) can always be compared against later, refactored versions on other branches.

No design pattern changes, interface restructuring, or other refactoring has been applied here. For the refactored 
version, see the `main` branch.

## Contents

This branch contains 14 Java source files, organized informally into four areas (there is no package structure):

|       Area        | Classes                                                                                   |
|:-----------------:|-------------------------------------------------------------------------------------------|
|   Domain model    | `Presentation`, `Slide`, `SlideItem`, `TextItem`, `BitmapItem`, `Style`                   |
| Data Access Layer | `Accessor`, `XMLAccessor`, `DemoPresentation`                                             |
|  User interface   | `SlideViewerFrame`, `SlideViewerComponent`, `MenuController`, `KeyController`, `AboutBox` |
|    Entry point    | `JabberPoint`                                                                             |

## What the Application Does

- Opens either a built-in demo presentation or a presentation loaded from an XML file
- Displays one slide at a time, each with a title and an ordered list of items (text lines or bitmap images)
- Supports navigation between slides via keyboard or the View menu
- Supports opening, saving, and starting a new presentation via the File menu
- Provides a "Go to slide" function
- Shows an About dialog

## Running

Compile and run `JabberPoint.main`, optionally passing an XML presentation file as the first argument. With no argument, the built-in demo presentation loads.
