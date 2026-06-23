# SoftwareQuality
This GitHub repository is the project submission for the Software Quality Course for Academic Year 2025-2026

## Student Information
Name: Nathan P. Pais D'costa
<br>
Student Number: 5045800

Name: Stefani Margaritova
<br>
Student Number: 4967399

## About this Branch

This branch (`main`) contains the **refactored version** of JabberPoint. Starting from the original baseline 
(see the `JabberpointV1` branch), this version applies four GoF design patterns and one interface segregation fix to 
resolve a set of SOLID principle violations identified in the original design.

## The Five Refactoring Changes

| # | Pattern / Fix         | Target Class(es)                                        | Resolves                       |
|---|-----------------------|---------------------------------------------------------|--------------------------------|
| 1 | Interface split (ISP) | `Accessor` → `PresentationLoader` / `PresentationSaver` | ISP, LSP                       |
| 2 | Factory               | `XMLAccessor` (loading)                                 | OCP                            |
| 3 | Strategy              | `SlideItem` subtypes, `XMLAccessor` (saving)            | OCP                            |
| 4 | Observer              | `Presentation`, `SlideViewerComponent`                  | Decouples UI from domain model |
| 5 | Command               | `MenuController`                                        | SRP, DIP                       |

## Contents

This branch contains the original 14 Java source files plus new classes and interfaces introduced by the refactor, 
organized into the same four broad areas as the original, with persistence renamed below to reflect its 
expanded structure.

| Area                      | Classes                                                                                                                                                                    |
|---------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Domain model              | `Presentation`, `Slide`, `SlideItem`, `TextItem`, `BitmapItem`, `Style`                                                                                                    |
| Data access (persistence) | `Accessor`, `XMLAccessor`, `DemoPresentation`, `PresentationLoader`, `PresentationSaver`, plus a `SlideItem` factory for loading and per-type save logic for serialization |
| User interface            | `SlideViewerFrame`, `SlideViewerComponent`, `MenuController`, `KeyController`, `AboutBox`, plus a set of `Command` classes (one per menu action)                           |
| Entry point               | `JabberPoint`                                                                                                                                                              |

## What Changed From the Original

- **`Accessor` split into `PresentationLoader` and `PresentationSaver`.** `DemoPresentation` now implements only `PresentationLoader`, since it never supported saving. This removes the broken `saveFile` that used to throw an exception unconditionally.
- **`XMLAccessor`'s slide-item loading uses a Factory.** Adding a new kind of `SlideItem` no longer requires editing `XMLAccessor` directly.
- **`XMLAccessor`'s slide-item saving uses Strategy.** Each `SlideItem` is responsible for its own serialization, rather than `XMLAccessor` checking each item's concrete type.
- **`Presentation` and `SlideViewerComponent` use Observer.** The view registers as an observer and is notified when the current slide changes, instead of being tightly coupled to `Presentation`.
- **`MenuController`'s menu actions use Command.** Each action (Open, Save, New, Exit, Next, Prev, GoTo, About) is its own `Command` object. `MenuController` only builds menus and binds commands; it no longer performs file I/O or constructs `XMLAccessor` directly.

## What the Application Does

Functionally unchanged from the original: opens a demo or XML-loaded presentation, displays one slide at a time, 
supports navigation and file open/save/new via the menu, a "Go to slide" function, and an About dialog. The refactor 
changes internal structure, not external behavior.

## Running

Compile and run `JabberPoint.main`, optionally passing an XML presentation file as the first argument. 
With no argument, the built-in demo presentation loads.
