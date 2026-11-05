package com.example.weekendwize

object DataRepository {

    val subjects = listOf(
        // ============================
        // 1. MATHEMATICS
        // ============================
        Subject(
            "Mathematics",
            "Grade 11",
            listOf(
                Topic(
                    "Algebraic Expressions",
                    listOf(
                        SubTopic(
                            "Simplifying Expressions",
                            Quiz(listOf(
                                Question("What is 2x + 3x?", listOf("4x", "5x", "6x", "7x"), 1),
                                Question("Solve for x: x - 5 = 0", listOf("0", "5", "-5", "10"), 1),
                                Question("Simplify: (x³)²", listOf("x⁵", "x⁶", "x⁹", "x¹"), 1)
                            )),
                            theory = "Simplifying expressions involves combining like terms. Like terms have the same variables raised to the same powers.",
                            notes = "Always group terms with the same variable before adding or subtracting their coefficients."
                        ),
                        SubTopic(
                            "Factoring & Equations",
                            Quiz(listOf(
                                Question("Factor x² - 4", listOf("(x - 2)(x + 2)", "(x - 2)(x - 2)", "(x + 2)(x + 2)", "x(x - 4)"), 0),
                                Question("Solve x² - 9 = 0", listOf("x = 3", "x = -3", "x = ±3", "x = 9"), 2),
                                Question("What is the quadratic formula?", listOf("x = -b ± √(b²-4ac) / 2a", "x = b ± √(a²-4bc)", "x = -a ± √(b²-4ac)", "x = -b / 2a"), 0)
                            )),
                            theory = "Factoring is the process of breaking down a polynomial into a product of simpler polynomials.",
                            notes = "Difference of squares formula: a² - b² = (a - b)(a + b)."
                        )
                    )
                ),
                Topic(
                    "Functions & Graphs",
                    listOf(
                        SubTopic(
                            "Linear & Quadratic Functions",
                            Quiz(listOf(
                                Question("What is the gradient of y = 3x + 2?", listOf("2", "3", "3x", "5"), 1),
                                Question("What is the turning point of y = (x - 2)² + 3?", listOf("(2, 3)", "(-2, 3)", "(2, -3)", "(-2, -3)"), 0),
                                Question("Where does y = x² - 4 cross the y-axis?", listOf("(0, 4)", "(0, -4)", "(4, 0)", "(-4, 0)"), 1)
                            )),
                            theory = "Linear functions produce straight lines y = mx + c. Quadratic functions form parabolas y = a(x-p)² + q.",
                            notes = "The turning point of y = a(x-p)² + q is always located at (p, q)."
                        ),
                        SubTopic(
                            "Hyperbolic & Exponential Functions",
                            Quiz(listOf(
                                Question("What is the vertical asymptote of y = 2/(x - 1) + 3?", listOf("x = 1", "y = 3", "x = -1", "y = -3"), 0),
                                Question("What is the horizontal asymptote of y = 2/(x - 1) + 3?", listOf("x = 1", "y = 3", "x = -1", "y = -3"), 1),
                                Question("In y = a·2ⁿ + q, if a > 0, the graph is:", listOf("Increasing", "Decreasing", "Vertical", "Horizontal"), 0)
                            )),
                            theory = "Hyperbolas y = a/(x-p) + q have vertical asymptote x = p and horizontal asymptote y = q.",
                            notes = "Exponential graphs never touch their horizontal asymptote y = q."
                        )
                    )
                ),
                Topic(
                    "Trigonometry & Geometry",
                    listOf(
                        SubTopic(
                            "Trigonometric Identities & Ratios",
                            Quiz(listOf(
                                Question("What is sin²θ + cos²θ equal to?", listOf("0", "1", "tanθ", "2"), 1),
                                Question("Express tanθ in terms of sinθ and cosθ:", listOf("cosθ/sinθ", "sinθ·cosθ", "sinθ/cosθ", "1/sinθ"), 2),
                                Question("In a right-angled triangle, sinθ is:", listOf("adj/hyp", "opp/hyp", "opp/adj", "hyp/opp"), 1)
                            )),
                            theory = "Trigonometric ratios relate angles to sides in right-angled triangles. Pythagorean identity: sin²θ + cos²θ = 1.",
                            notes = "CAST diagram helps determine which ratios are positive in each of the 4 quadrants."
                        ),
                        SubTopic(
                            "Sine, Cosine & Area Rules",
                            Quiz(listOf(
                                Question("The Sine Rule is used when we know:", listOf("3 sides only", "2 sides and included angle", "2 angles and a side", "Right angle only"), 2),
                                Question("The Cosine Rule formula for side 'a' is:", listOf("a² = b² + c² - 2bc cosA", "a = b + c - cosA", "a² = b² - c²", "a = sinA / sinB"), 0),
                                Question("Area of ΔABC is calculated as:", listOf("½ ab sinC", "ab cosC", "½ (a+b) h", "a² + b²"), 0)
                            )),
                            theory = "The Sine, Cosine, and Area rules are applicable to non-right-angled triangles.",
                            notes = "Use Cosine Rule when given 3 sides or 2 sides and the included angle."
                        )
                    )
                )
            ),
            theory = "MATHEMATICS THEORY - GRADE 11\n\n1. ALGEBRAIC EXPRESSIONS:\n- Exponents: Laws of exponents (a^m * a^n = a^(m+n)).\n- Surds & Equations: Quadratic formula x = [-b ± √(b² - 4ac)] / 2a.\n2. FUNCTIONS:\n- Linear (y = mx + c), Quadratic (y = a(x-p)² + q), Hyperbolic, and Exponential graphs.\n3. TRIGONOMETRY:\n- Identities, reduction formulae, Sine, Cosine, and Area Rules.",
            notes = "MATHEMATICS QUICK REVISION NOTES\n\n- Quadratic Formula Tip: If b² - 4ac < 0, roots are non-real.\n- Functions: 'q' shifts graphs vertically.\n- Trigonometry: sin is positive in 1st & 2nd quadrants.",
            revision = "MATHEMATICS EXAM PREPARATION GUIDE\n\nPaper 1: Algebra (25m), Patterns (25m), Functions (35m), Finance (15m), Probability (20m).\nPaper 2: Statistics (20m), Analytical Geometry (30m), Trig (50m), Euclidean Geometry (50m)."
        ),

        // ============================
        // 2. PHYSICAL SCIENCES
        // ============================
        Subject(
            "Physical Sciences",
            "Grade 11",
            listOf(
                Topic(
                    "Newton's Laws",
                    listOf(
                        SubTopic(
                            "Newton's First & Second Laws",
                            Quiz(listOf(
                                Question("Newton's First Law is also known as the Law of:", listOf("Gravitation", "Inertia", "Action-Reaction", "Momentum"), 1),
                                Question("What is the formula for Newton's Second Law?", listOf("F = mv", "Fnet = ma", "W = FΔx", "p = mv"), 1),
                                Question("If net force doubles while mass remains constant, acceleration:", listOf("Halves", "Doubles", "Quadruples", "Stays same"), 1)
                            )),
                            theory = "Newton's First Law states an object remains at rest or constant velocity unless acted upon by a net force. Second Law: Fnet = ma.",
                            notes = "Always draw a clear Free Body Diagram (FBD) before calculating forces."
                        ),
                        SubTopic(
                            "Newton's Third Law & Gravitation",
                            Quiz(listOf(
                                Question("Newton's Third Law states action and reaction forces are:", listOf("Equal and in same direction", "Equal in magnitude, opposite in direction", "Unequal and opposite", "Zero"), 1),
                                Question("Universal Gravitation formula is:", listOf("F = G(m1·m2)/r²", "F = mg", "F = G m / r", "F = m1·m2 / r"), 0),
                                Question("If distance 'r' between two masses doubles, gravitational force becomes:", listOf("Double", "Half", "One-fourth", "Four times"), 2)
                            )),
                            theory = "Third Law: Action-reaction pairs act on different objects. Universal Gravitation F = G(m1·m2)/r².",
                            notes = "Gravitational acceleration on Earth g = 9.8 m/s² downwards."
                        )
                    )
                ),
                Topic(
                    "Chemical Bonding",
                    listOf(
                        SubTopic(
                            "Molecular Structure & Intermolecular Forces",
                            Quiz(listOf(
                                Question("Atoms bond in order to achieve:", listOf("Higher energy", "Octet/Stable electron configuration", "More protons", "Ionic charge"), 1),
                                Question("Which intermolecular force is strongest in water (H₂O)?", listOf("London forces", "Dipole-dipole", "Hydrogen bonding", "Covalent bond"), 2),
                                Question("What is the molecular shape of methane (CH₄)?", listOf("Linear", "Trigonal planar", "Tetrahedral", "Bent"), 2)
                            )),
                            theory = "Chemical bonding lowers potential energy. Intermolecular forces (Hydrogen bonding, Dipole-dipole, London forces) determine physical properties.",
                            notes = "Electronegativity difference > 1.7 forms ionic bonds, < 0.5 forms non-polar covalent bonds."
                        ),
                        SubTopic(
                            "Stoichiometry & Ideal Gases",
                            Quiz(listOf(
                                Question("What is the Ideal Gas equation?", listOf("PV = nRT", "P1V1 = P2V2", "V1/T1 = V2/T2", "P/T = K"), 0),
                                Question("Molar volume of any gas at STP is:", listOf("22.4 dm³/mol", "24.0 dm³/mol", "1.0 dm³/mol", "22.4 cm³/mol"), 0),
                                Question("The formula to calculate moles from mass is:", listOf("n = m / M", "n = m · M", "n = M / m", "n = C · V"), 0)
                            )),
                            theory = "Stoichiometry involves quantitative calculations of reactants and products. Ideal gases obey PV = nRT.",
                            notes = "Always balance chemical equations before calculating mole ratios."
                        )
                    )
                )
            ),
            theory = "PHYSICAL SCIENCES THEORY - GRADE 11\n\n1. PHYSICS: Newton's Laws (1st, 2nd, 3rd) and Universal Gravitation F = G(m1·m2)/r².\n2. CHEMISTRY: Chemical Bonding, VSEPR theory, Intermolecular Forces, and Ideal Gas Law PV = nRT.",
            notes = "PHYSICAL SCIENCES KEY POINTS\n\n- Draw Free Body Diagrams for force problems.\n- Momentum p = mv is conserved in isolated systems.\n- Balance chemical equations before stoichiometric calculations.",
            revision = "PHYSICAL SCIENCES STUDY STRATEGY\n\nPhysics Paper 1: Vectors, Newton's Laws, Electrostatics, Electromagnetism.\nChemistry Paper 2: Bonding, Intermolecular Forces, Stoichiometry, Gases."
        ),

        // ============================
        // 3. LIFE SCIENCES
        // ============================
        Subject(
            "Life Sciences",
            "Grade 11",
            listOf(
                Topic(
                    "Photosynthesis",
                    listOf(
                        SubTopic(
                            "Light & Dark Phases",
                            Quiz(listOf(
                                Question("Where does the Light-Dependent Phase take place?", listOf("Stroma", "Thylakoids/Grana", "Mitochondria", "Ribosome"), 1),
                                Question("What gas is released as a byproduct during Photosynthesis?", listOf("Carbon dioxide", "Nitrogen", "Oxygen", "Hydrogen"), 2),
                                Question("The Light-Independent Phase (Calvin Cycle) occurs in the:", listOf("Grana", "Stroma", "Cell wall", "Vacuole"), 1)
                            )),
                            theory = "Photosynthesis converts light energy into chemical energy (glucose). Equation: 6CO₂ + 6H₂O + light → C₆H₁₂O₆ + 6O₂.",
                            notes = "Light phase produces ATP and NADPH; Dark phase uses CO₂ to synthesize glucose."
                        ),
                        SubTopic(
                            "Cellular Respiration",
                            Quiz(listOf(
                                Question("Cellular respiration primary organelle is:", listOf("Chloroplast", "Mitochondrion", "Golgi body", "Nucleus"), 1),
                                Question("Aerobic respiration requires which gas?", listOf("Carbon dioxide", "Oxygen", "Nitrogen", "Methane"), 1),
                                Question("The main energy currency produced in respiration is:", listOf("DNA", "RNA", "ATP", "Glucose"), 2)
                            )),
                            theory = "Cellular respiration breaks down glucose to release energy in the form of ATP.",
                            notes = "Aerobic respiration produces up to 36-38 ATP per glucose molecule."
                        )
                    )
                ),
                Topic(
                    "Animal Nutrition",
                    listOf(
                        SubTopic(
                            "Human Digestive System & Enzymes",
                            Quiz(listOf(
                                Question("Which organ produces bile?", listOf("Stomach", "Pancreas", "Liver", "Gallbladder"), 2),
                                Question("Protein digestion begins in the:", listOf("Mouth", "Stomach", "Small intestine", "Large intestine"), 1),
                                Question("Finger-like projections in small intestine for absorption are:", listOf("Cilia", "Villi", "Flagella", "Alveoli"), 1)
                            )),
                            theory = "Animal nutrition involves ingestion, digestion, absorption, assimilation, and egestion.",
                            notes = "Enzymes are biological catalysts that speed up digestion without being consumed."
                        ),
                        SubTopic(
                            "Absorption & Excretion",
                            Quiz(listOf(
                                Question("The functional unit of the human kidney is the:", listOf("Neuron", "Nephron", "Alveolus", "Villus"), 1),
                                Question("Main waste product excreted in human urine is:", listOf("Glucose", "Urea", "Protein", "Starch"), 1),
                                Question("Reabsorption of glucose occurs mainly in the:", listOf("Glomerulus", "Proximal convoluted tubule", "Collecting duct", "Ureter"), 1)
                            )),
                            theory = "Absorption occurs in the small intestine. Excretion removes metabolic wastes via the kidneys.",
                            notes = "Nephrons filter blood in the glomerulus and reabsorb essential nutrients."
                        )
                    )
                )
            ),
            theory = "LIFE SCIENCES THEORY - GRADE 11\n\n1. PHOTOSYNTHESIS: Light and Dark phases, equation, ATP production.\n2. ANIMAL NUTRITION: Ingestion, digestion, villi absorption, and excretion via kidneys.",
            notes = "LIFE SCIENCES STUDY TIPS\n\n- Practice drawing Chloroplast and Nephron diagrams.\n- Do not confuse Respiration (ATP making) with Breathing (ventilation).",
            revision = "LIFE SCIENCES REVISION CHECKLIST\n\nTerm 1: Micro-organisms & Plant Biodiversity.\nTerm 2: Photosynthesis & Animal Nutrition.\nTerm 3: Respiration, Gaseous Exchange, Excretion."
        ),

        // ============================
        // 4. ACCOUNTING
        // ============================
        Subject(
            "Accounting",
            "Grade 11",
            listOf(
                Topic(
                    "Partnerships",
                    listOf(
                        SubTopic(
                            "Partnership Formation & Ledgers",
                            Quiz(listOf(
                                Question("A Partnership is formed by:", listOf("1 person", "2 to 20 partners", "50+ shareholders", "Government"), 1),
                                Question("Where is partner profit/loss distributed?", listOf("Capital Account", "Appropriation Account", "Bank Account", "Trading Account"), 1),
                                Question("Interest on Capital is recorded as an expense to:", listOf("Partner", "Business Partnership", "Bank", "Creditor"), 1)
                            )),
                            theory = "Partnerships combine capital and skills of 2-20 partners. Financial statements include Appropriation Account.",
                            notes = "Current Accounts record drawings, salary allowances, and shared profits for each partner."
                        ),
                        SubTopic(
                            "Financial Statements & Analysis",
                            Quiz(listOf(
                                Question("The Accounting Equation is:", listOf("Assets = Liabilities + Equity", "Assets = Liabilities - Equity", "Equity = Assets + Liabilities", "Income = Expenses"), 0),
                                Question("Current Ratio measures:", listOf("Profitability", "Liquidity", "Solvency", "Efficiency"), 1),
                                Question("Acid-Test Ratio excludes which asset?", listOf("Cash", "Debtors", "Inventories", "Bank"), 2)
                            )),
                            theory = "Financial Statements evaluate performance (Income Statement) and financial position (Balance Sheet).",
                            notes = "Acid-Test Ratio = (Current Assets - Inventory) : Current Liabilities."
                        )
                    )
                ),
                Topic(
                    "Cost Accounting",
                    listOf(
                        SubTopic(
                            "Manufacturing Costs",
                            Quiz(listOf(
                                Question("Raw materials used in production are categorized as:", listOf("Indirect costs", "Direct costs", "Fixed costs", "Selling costs"), 1),
                                Question("Factory supervisor salary is an example of:", listOf("Direct labor", "Factory overhead", "Administration cost", "Raw material"), 1),
                                Question("Total Production Cost formula is:", listOf("Direct Materials + Direct Labor + Factory Overheads", "Sales - Expenses", "Assets - Liabilities", "Direct Labor + Fixed Costs"), 0)
                            )),
                            theory = "Manufacturing Accounting divides costs into Direct Materials, Direct Labor, and Factory Overheads.",
                            notes = "Prime Cost = Direct Materials + Direct Labor."
                        ),
                        SubTopic(
                            "Break-Even Analysis & Budgets",
                            Quiz(listOf(
                                Question("Break-Even Point is where:", listOf("Profit is maximum", "Total Revenue = Total Costs", "Loss is maximum", "Sales are zero"), 1),
                                Question("Cash Budget predicts future:", listOf("Net Profit", "Cash Inflows & Outflows", "Capital Growth", "Inventory levels"), 1),
                                Question("Variable costs change in direct proportion to:", listOf("Time", "Production volume", "Interest rates", "Rent"), 1)
                            )),
                            theory = "Break-Even Point shows minimum production required to avoid losses. Cash Budgets forecast liquidity.",
                            notes = "Break-Even Quantity = Fixed Costs / (Selling Price per unit - Variable Cost per unit)."
                        )
                    )
                )
            ),
            theory = "ACCOUNTING THEORY - GRADE 11\n\n1. PARTNERSHIPS: Appropriation accounts, Current & Capital ledgers, Balance Sheet.\n2. COST ACCOUNTING: Direct vs Indirect costs, Factory Overheads, Break-Even analysis, Cash Budgets.",
            notes = "ACCOUNTING KEY CONCEPTS\n\n- Double Entry rule: Debits on left, Credits on right.\n- Always calculate Prime Cost first in Cost Accounting.",
            revision = "ACCOUNTING EXAM TIPS\n\nPaper 1: Financial Statements, Analysis & Interpretation, Ethics.\nPaper 2: Cost Accounting, Budgeting, Reconciliations, VAT."
        ),

        // ============================
        // 5. BUSINESS STUDIES
        // ============================
        Subject(
            "Business Studies",
            "Grade 11",
            listOf(
                Topic(
                    "Business Environments",
                    listOf(
                        SubTopic(
                            "Micro & Market Environments",
                            Quiz(listOf(
                                Question("Which environment is internal and fully controllable by business?", listOf("Macro", "Market", "Micro", "Global"), 2),
                                Question("Competitors and Suppliers belong to which environment?", listOf("Micro", "Market", "Macro", "Internal"), 1),
                                Question("In SWOT Analysis, Strengths and Weaknesses are:", listOf("External", "Internal", "Uncontrollable", "Global"), 1)
                            )),
                            theory = "Micro environment (vision, mission, resources) is fully controllable. Market environment (customers, suppliers, competitors) is semi-controllable.",
                            notes = "Use SWOT analysis for Micro/Market environments and PESTLE for Macro environment."
                        ),
                        SubTopic(
                            "Macro Environment & PESTLE",
                            Quiz(listOf(
                                Question("In PESTLE analysis, 'P' stands for:", listOf("Product", "Political", "Price", "Public"), 1),
                                Question("Inflation rates and interest rates fall under which PESTLE factor?", listOf("Social", "Technological", "Economic", "Legal"), 2),
                                Question("Businesses can control the Macro Environment:", listOf("True", "False", "Partially", "Only in winter"), 1)
                            )),
                            theory = "Macro environment (Political, Economic, Social, Technological, Legal, Environmental) is outside business control.",
                            notes = "Businesses must adapt to changes in the Macro environment to remain profitable."
                        )
                    )
                ),
                Topic(
                    "Business Ventures & Leadership",
                    listOf(
                        SubTopic(
                            "Entrepreneurship & Business Plans",
                            Quiz(listOf(
                                Question("A key characteristic of a successful entrepreneur is:", listOf("Risk aversion", "Risk-taking and innovation", "Fear of change", "Dependency"), 1),
                                Question("A Business Plan is created to:", listOf("Pay taxes", "Secure funding and guide operations", "Hire employees only", "Close down"), 1),
                                Question("An Executive Summary in a business plan is placed at the:", listOf("Beginning", "Middle", "End", "Appendix"), 0)
                            )),
                            theory = "Entrepreneurship involves identifying business opportunities and creating ventures. Business plans outline operational strategies.",
                            notes = "A business plan includes marketing, operational, financial, and management plans."
                        ),
                        SubTopic(
                            "Leadership & Management Styles",
                            Quiz(listOf(
                                Question("A leader who makes all decisions without consulting staff is:", listOf("Democratic", "Autocratic", "Laissez-faire", "Charismatic"), 1),
                                Question("Democratic leadership style encourages:", listOf("No communication", "Employee participation in decision making", "Total chaos", "Strict dictatorship"), 1),
                                Question("Laissez-faire leadership means leaders:", listOf("Micromanage staff", "Give freedom to employees to make decisions", "Rule by fear", "Do all work themselves"), 1)
                            )),
                            theory = "Leadership inspires and guides people; Management plans, organizes, leads, and controls resources.",
                            notes = "Different situations require different leadership styles (Situational Leadership)."
                        )
                    )
                )
            ),
            theory = "BUSINESS STUDIES THEORY - GRADE 11\n\n1. BUSINESS ENVIRONMENTS: Micro, Market, and Macro (PESTLE).\n2. VENTURES & MANAGEMENT: Entrepreneurship, Business Plans, Leadership styles (Autocratic, Democratic, Laissez-faire).",
            notes = "BUSINESS STUDIES KEY REVISION\n\n- SWOT = Internal (Strengths/Weaknesses), External (Opportunities/Threats).\n- PESTLE = Macro environment analysis tool.",
            revision = "BUSINESS STUDIES EXAM STRATEGY\n\nFocus on case studies. State the fact, explain the impact on the business, and suggest practical recommendations."
        ),

        // ============================
        // 6. GEOGRAPHY
        // ============================
        Subject(
            "Geography",
            "Grade 11",
            listOf(
                Topic(
                    "Geomorphology",
                    listOf(
                        SubTopic(
                            "Slope Elements & Development",
                            Quiz(listOf(
                                Question("Which slope element is steep, vertical rock at the top?", listOf("Crest", "Scarp / Cliff", "Talus / Debris slope", "Pediment"), 1),
                                Question("The gentle slope at the base of a mountain is called the:", listOf("Crest", "Cliff", "Talus", "Pediment"), 3),
                                Question("Slopes formed in horizontal strata produce landscape features called:", listOf("Fold mountains", "Mesas and Buttes", "Volcanoes", "Rift valleys"), 1)
                            )),
                            theory = "Slope elements include Crest, Free Face (Scarp), Talus (Debris), and Pediment. Horizontal strata form Mesas and Buttes.",
                            notes = "Mesa is flat-topped and wider than it is high; Butte is taller than it is wide."
                        ),
                        SubTopic(
                            "Mass Movements & Erosion",
                            Quiz(listOf(
                                Question("The fastest type of mass movement is a:", listOf("Soil creep", "Rockfall / Landslide", "Solifluction", "Mudflow"), 1),
                                Question("Soil creep is characterized by:", listOf("Rapid movement", "Extremely slow downslope movement", "Volcanic activity", "Glacial flow"), 1),
                                Question("A major trigger for mass movement on steep slopes is:", listOf("Heavy rainfall", "Drought", "Planting trees", "Sunshine"), 0)
                            )),
                            theory = "Mass movement is the downslope movement of soil and rock under gravity, triggered by saturation and steep slopes.",
                            notes = "Trees and vegetation help stabilize slopes and reduce mass movement risks."
                        )
                    )
                ),
                Topic(
                    "Climatology & Atmosphere",
                    listOf(
                        SubTopic(
                            "Global Air Circulation & Pressure Cells",
                            Quiz(listOf(
                                Question("Air pressure at the Equator is generally:", listOf("High", "Low", "Constant zero", "Fluctuating wildly"), 1),
                                Question("Subtropical High Pressure belts over South Africa cause:", listOf("Heavy rain", "Clear, dry weather", "Hurricanes", "Tornadoes"), 1),
                                Question("The Kalahari High Pressure cell dominates South Africa in:", listOf("Summer", "Winter", "Spring", "Autumn"), 1)
                            )),
                            theory = "Global circulation driven by Hadley, Ferrel, and Polar cells. High pressure produces sinking air and dry conditions.",
                            notes = "Kalahari High Pressure cell sinks in winter, causing dry, sunny weather over interior plateau."
                        ),
                        SubTopic(
                            "Synoptic Weather Maps & Rainfall",
                            Quiz(listOf(
                                Question("Lines joining points of equal atmospheric pressure on weather maps are:", listOf("Isotherms", "Isobars", "Contour lines", "Isohyets"), 1),
                                Question("When cold air pushes underneath warm air, it forms a:", listOf("Warm front", "Cold front", "Stationary front", "Occluded front"), 1),
                                Question("Frontal (cyclonic) rainfall in the Western Cape is associated with:", listOf("Mid-latitude cyclones", "Tropical cyclones", "Monsoons", "Tornadoes"), 0)
                            )),
                            theory = "Synoptic weather maps show pressure systems, winds, and fronts. Mid-latitude cyclones bring winter rain to Western Cape.",
                            notes = "Closely spaced isobars indicate strong, gusty winds."
                        )
                    )
                )
            ),
            theory = "GEOGRAPHY THEORY - GRADE 11\n\n1. GEOMORPHOLOGY: Slope elements (Crest, Scarp, Talus, Pediment), Mesas, Buttes, and Mass Movements.\n2. CLIMATOLOGY: Global pressure cells, Kalahari High, Synoptic weather maps, Mid-latitude cyclones.",
            notes = "GEOGRAPHY QUICK NOTES\n\n- Mesas have width > height. Buttes have height > width.\n- Cold fronts bring rain, temperature drop, and wind direction changes.",
            revision = "GEOGRAPHY MAPWORK & EXAM TIPS\n\nPaper 1: Climate & Weather, Geomorphology.\nPaper 2: Mapwork calculations, GIS, and Settlement Geography."
        )
    )
}
